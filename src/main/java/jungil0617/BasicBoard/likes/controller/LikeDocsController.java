package jungil0617.BasicBoard.likes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;

public interface LikeDocsController {

    @Operation(
            summary = "좋아요 토글",
            responses = {
                    @ApiResponse(responseCode = "200", description = "좋아요 상태 변경 성공"),
                    @ApiResponse(responseCode = "401", description = "JWT 인증 실패"),
                    @ApiResponse(responseCode = "404", description = "게시글 또는 유저를 찾을 수 없음")
            }
    )
    ResponseEntity<String> like(@PathVariable Long postId, Authentication authentication);

    @Operation(
            summary = "좋아요 개수 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "좋아요 개수 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
            }
    )
    ResponseEntity<Long> getLikeCount(@PathVariable Long postId);

}