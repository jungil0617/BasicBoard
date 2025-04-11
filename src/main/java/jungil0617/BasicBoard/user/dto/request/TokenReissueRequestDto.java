package jungil0617.BasicBoard.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TokenReissueRequestDto(
        @NotBlank(message = "토큰이 비어있습니다.") String refreshToken) {
}