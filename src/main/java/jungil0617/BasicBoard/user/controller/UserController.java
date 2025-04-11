package jungil0617.BasicBoard.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jungil0617.BasicBoard.user.dto.request.TokenReissueRequestDto;
import jungil0617.BasicBoard.user.dto.request.UserLoginRequestDto;
import jungil0617.BasicBoard.user.dto.request.UserNicknameUpdateRequestDto;
import jungil0617.BasicBoard.user.dto.request.UserSignupRequestDto;
import jungil0617.BasicBoard.user.dto.response.TokenResponse;
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
public class UserController implements UserDocsController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserSignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody UserLoginRequestDto requestDto) {
        TokenResponse response = userService.login(requestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(Authentication authentication) {
        String username = authentication.getName();
        userService.logout(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<String> getMyInfo(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(username);
    }

    @PatchMapping("/nickname")
    public ResponseEntity<String> updateNickname(
            Authentication authentication,
            @Valid @RequestBody UserNicknameUpdateRequestDto requestDto) {

        String username = authentication.getName();
        userService.updateNickname(username, requestDto.nickname());
        return ResponseEntity.ok(requestDto.nickname());
    }

    @PostMapping("/reissue")
    public ResponseEntity<String> reissue(@Valid @RequestBody TokenReissueRequestDto request) {
        String newAccessToken = userService.reissueAccessToken(request.refreshToken());
        return ResponseEntity.ok(newAccessToken);
    }

}