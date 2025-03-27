package jungil0617.BasicBoard.post.dto;

import jungil0617.BasicBoard.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostListResponseDto {

    private Long postId;
    private String title;
    private int viewCount;
    private LocalDateTime createdAt;

    public static PostListResponseDto fromEntity(Post post) {
        return new PostListResponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getViewCount(),
                post.getCreatedAt()
        );
    }

}