package jungil0617.BasicBoard.saves.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jungil0617.BasicBoard.saves.service.SaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/saves")
public class SaveController {

    private final SaveService saveService;

    @PostMapping
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

}