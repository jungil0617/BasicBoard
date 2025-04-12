package jungil0617.BasicBoard.comment.dto.response;

import jungil0617.BasicBoard.comment.entity.Comment;

import java.time.LocalDateTime;

public record CommentResponseDto(
        Long commentId,
        String content,
        String nickname,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CommentResponseDto fromEntity(Comment comment) {
        return new CommentResponseDto(
                comment.getCommentId(),
                comment.getContent(),
                comment.getUser().getNickname(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

}