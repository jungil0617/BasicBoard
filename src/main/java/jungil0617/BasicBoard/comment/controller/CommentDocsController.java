package jungil0617.BasicBoard.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jungil0617.BasicBoard.comment.dto.request.CommentRequestDto;
import jungil0617.BasicBoard.comment.dto.response.CommentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CommentDocsController {

    @Operation(
            summary = "댓글 작성",
            responses = {
                    @ApiResponse(responseCode = "201", description = "댓글 작성 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "JWT 인증 실패"),
                    @ApiResponse(responseCode = "404", description = "게시글 또는 유저를 찾을 수 없음")
            }
    )
    ResponseEntity<String> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequestDto requestDto,
            Authentication authentication);

    @Operation(
            summary = "댓글 목록 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "댓글 목록 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
            }
    )
    ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long postId);

    @Operation(
            summary = "댓글 수정",
            responses = {
                    @ApiResponse(responseCode = "200", description = "댓글 수정 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "JWT 인증 실패"),
                    @ApiResponse(responseCode = "403", description = "작성자가 아님"),
                    @ApiResponse(responseCode = "404", description = "댓글 또는 유저를 찾을 수 없음")
            }
    )
    ResponseEntity<String> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequestDto requestDto,
            Authentication authentication);

    @Operation(
            summary = "댓글 삭제",
            responses = {
                    @ApiResponse(responseCode = "204", description = "댓글 삭제 성공"),
                    @ApiResponse(responseCode = "401", description = "JWT 인증 실패"),
                    @ApiResponse(responseCode = "403", description = "작성자가 아님"),
                    @ApiResponse(responseCode = "404", description = "댓글 또는 유저를 찾을 수 없음")
            }
    )
    ResponseEntity<Void> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            Authentication authentication);

}