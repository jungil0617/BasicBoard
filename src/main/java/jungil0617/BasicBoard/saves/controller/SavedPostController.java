package jungil0617.BasicBoard.saves.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jungil0617.BasicBoard.post.dto.PostListResponseDto;
import jungil0617.BasicBoard.saves.service.SaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/saves")
public class SavedPostController {

    private final SaveService saveService;

    @GetMapping
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