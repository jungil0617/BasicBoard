package jungil0617.BasicBoard.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDto(
        @NotBlank(message = "아이디가 비어있습니다.") String username,
        @NotBlank(message = "비밀번호가 비어있습니다.") String password) {
}