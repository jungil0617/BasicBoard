package jungil0617.BasicBoard.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jungil0617.BasicBoard.user.dto.UserLoginRequestDto;
import jungil0617.BasicBoard.user.dto.UserSignupRequestDto;
import jungil0617.BasicBoard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User Api")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(
        summary = "회원가입",
        responses = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "중복된 사용자명 또는 유효하지 않은 값")}
    )
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입");
    }

    @PostMapping("/login")
    @Operation(
        summary = "로그인",
        responses = {
            @ApiResponse(responseCode = "200", description = "로그인 성공 (AccessToken 반환)"),
            @ApiResponse(responseCode = "400", description = "비밀번호 불일치 또는 사용자 없음")}
    )
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDto requestDto) {
        String accessToken = userService.login(requestDto);
        return ResponseEntity.ok(accessToken);
    }

    @GetMapping("/me")
    @Operation(
        summary = "내 정보 조회",
        description = "로그인한 사용자의 username 반환",
        responses = {
            @ApiResponse(responseCode = "200", description = "인증된 사용자 정보 반환"),
            @ApiResponse(responseCode = "401", description = "JWT가 없거나 유효하지 않음")}
    )
    public ResponseEntity<String> getMyInfo(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(username);
    }

}