package jungil0617.BasicBoard.likes.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jungil0617.BasicBoard.likes.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/likes")
@Tag(name = "Like Api")
public class LikeController implements LikeDocsController{

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<String> like(@PathVariable Long postId, Authentication authentication) {
        String username = authentication.getName();

        boolean isLiked = likeService.like(username, postId);

        String message = isLiked ? "좋아요" : "좋아요 취소";
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<Long> getLikeCount(@PathVariable Long postId) {
        Long count = likeService.getLikeCount(postId);
        return ResponseEntity.ok(count);
    }

}