package jungil0617.BasicBoard.post.dto;

import java.time.LocalDateTime;

public record PostResponseDto(
        Long postId,
        String title,
        String content,
        String nickname,
        int viewCount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}