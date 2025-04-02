package jungil0617.BasicBoard.saves.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jungil0617.BasicBoard.post.dto.PostListResponseDto;
import jungil0617.BasicBoard.saves.service.SaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class SaveController {

    private final SaveService saveService;

    @PostMapping("/{postId}/saves")
    @Operation(
        summary = "게시글 저장 토글",
        responses = {
            @ApiResponse(responseCode = "200", description = "게시글 저장 또는 저장 취소 성공"),
            @ApiResponse(responseCode = "401", description = "JWT 인증 실패"),
            @ApiResponse(responseCode = "404", description = "게시글 또는 유저를 찾을 수 없음")
        }
    )
    public ResponseEntity<String> save(@PathVariable Long postId, Authentication authentication) {
        String username = authentication.getName();
        boolean isSaved = saveService.save(username, postId);

        String message = isSaved ? "저장" : "저장 취소";
        return ResponseEntity.ok(message);
    }

    @GetMapping("/saves")
    @Operation(
            summary = "저장한 게시글 목록 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "저장한 게시글 목록 조회 성공"),
                    @ApiResponse(responseCode = "401", description = "JWT 인증 실패")
            }
    )
    public ResponseEntity<List<PostListResponseDto>> getSavedPosts(Authentication authentication) {
        String username = authentication.getName();
        List<PostListResponseDto> savedPosts = saveService.getSavedPosts(username);
        return ResponseEntity.ok(savedPosts);
    }

}