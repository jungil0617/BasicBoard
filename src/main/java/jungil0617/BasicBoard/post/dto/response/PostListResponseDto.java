package jungil0617.BasicBoard.post.dto.response;

import jungil0617.BasicBoard.post.entity.Post;

import java.time.LocalDateTime;

public record PostListResponseDto (
        Long postId,
        String title,
        int viewCount,
        LocalDateTime createdAt
){
    public static PostListResponseDto fromEntity(Post post) {
        return new PostListResponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getViewCount(),
                post.getCreatedAt()
        );
    }

}