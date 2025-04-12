package jungil0617.BasicBoard.post.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PostRequestDto(
        @NotBlank(message = "제목이 비어있습니다.") String title,
        @NotBlank(message = "내용이 비어있습니다.") String content) {
}