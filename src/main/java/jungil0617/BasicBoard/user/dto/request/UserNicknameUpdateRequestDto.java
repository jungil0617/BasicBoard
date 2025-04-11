package jungil0617.BasicBoard.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserNicknameUpdateRequestDto(
        @NotBlank(message = "닉네임이 비어있습니다.") String nickname) {
}