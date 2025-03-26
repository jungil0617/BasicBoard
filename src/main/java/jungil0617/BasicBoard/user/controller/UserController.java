package jungil0617.BasicBoard.user.controller;

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
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDto requestDto) {
        String accessToken = userService.login(requestDto);
        return ResponseEntity.ok(accessToken);
    }

    @GetMapping("/me")
    public ResponseEntity<String> getMyInfo(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(username);
    }

}