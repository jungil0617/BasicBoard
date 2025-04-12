package jungil0617.BasicBoard.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jungil0617.BasicBoard.post.dto.request.PostRequestDto;
import jungil0617.BasicBoard.post.dto.response.PostListResponseDto;
import jungil0617.BasicBoard.post.dto.response.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface PostDocsController {

    @Operation(
            summary = "게시글 작성",
            responses = {
                    @ApiResponse(responseCode = "201", description = "게시글 작성 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "JWT 인증 실패")
            }
    )
    ResponseEntity<String> createPost(
            @Valid @RequestBody PostRequestDto requestDto,
            Authentication authentication);

    @Operation(
            summary = "게시글 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "게시글 상세 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음"),
                    @ApiResponse(responseCode = "401", description = "JWT 인증 실패")
            }
    )
    ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId);

    @Operation(
            summary = "게시글 수정",
            responses = {
                    @ApiResponse(responseCode = "200", description = "게시글 수정 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "JWT 인증 실패"),
                    @ApiResponse(responseCode = "403", description = "작성자가 아님")
            }
    )
    ResponseEntity<String> updatePost(@PathVariable Long postId, @Valid @RequestBody PostRequestDto requestDto, Authentication authentication);

    @Operation(
            summary = "게시글 삭제",
            responses = {
                    @ApiResponse(responseCode = "204", description = "게시글 삭제 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "JWT 인증 실패"),
                    @ApiResponse(responseCode = "403", description = "작성자가 아님")
            }
    )
    ResponseEntity<Void> deletePost(@PathVariable Long postId, Authentication authentication);

    @Operation(
            summary = "게시글 목록 조회 (페이징, 정렬)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청")
            }
    )
    ResponseEntity<Page<PostListResponseDto>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "postId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction);
}