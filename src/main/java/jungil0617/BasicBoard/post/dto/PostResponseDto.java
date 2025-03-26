package jungil0617.BasicBoard.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponseDto {

    private Long postId;
    private String title;
    private String content;
    private String nickname;
    private int viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}