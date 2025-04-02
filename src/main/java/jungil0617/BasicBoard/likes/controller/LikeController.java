package jungil0617.BasicBoard.likes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    @Operation(
        summary = "좋아요 토글",
        responses = {
            @ApiResponse(responseCode = "200", description = "좋아요 상태 변경 성공"),
            @ApiResponse(responseCode = "401", description = "JWT 인증 실패"),
            @ApiResponse(responseCode = "404", description = "게시글 또는 유저를 찾을 수 없음")
        }
    )
    public ResponseEntity<String> like(@PathVariable Long postId, Authentication authentication) {
        String username = authentication.getName();

        boolean isLiked = likeService.like(username, postId);

        String message = isLiked ? "좋아요" : "좋아요 취소";
        return ResponseEntity.ok(message);
    }

    @GetMapping
    @Operation(
        summary = "좋아요 개수 조회",
        responses = {
            @ApiResponse(responseCode = "200", description = "좋아요 개수 조회 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
        }
    )
    public ResponseEntity<Long> getLikeCount(@PathVariable Long postId) {
        Long count = likeService.getLikeCount(postId);
        return ResponseEntity.ok(count);
    }

}