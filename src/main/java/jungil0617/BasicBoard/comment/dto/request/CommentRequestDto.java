package jungil0617.BasicBoard.comment.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CommentRequestDto(
        @NotBlank(message = "내용이 비어있습니다.") String comment) {
}