package jungil0617.BasicBoard.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jungil0617.BasicBoard.post.dto.PostListResponseDto;
import jungil0617.BasicBoard.post.dto.PostRequestDto;
import jungil0617.BasicBoard.post.dto.PostResponseDto;
import jungil0617.BasicBoard.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name = "Post Api")
public class PostController {

    private final PostService postService;

    @PostMapping
    @Operation(
        summary = "게시글 작성",
        responses = {
            @ApiResponse(responseCode = "201", description = "게시글 작성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "JWT 인증 실패")
        }
    )
    public ResponseEntity<String> createPost(
            @RequestBody PostRequestDto requestDto,
            Authentication authentication) {

        String username = authentication.getName();
        postService.createPost(username, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("게시글 작성 성공");
    }

    @GetMapping("/{postId}")
    @Operation(
        summary = "게시글 상세 조회",
        responses = {
            @ApiResponse(responseCode = "200", description = "게시글 상세 조회 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음"),
            @ApiResponse(responseCode = "401", description = "JWT 인증 실패")
        }
    )
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto postResponseDto = postService.getPost(postId);
        return ResponseEntity.ok(postResponseDto);
    }

    @PatchMapping("/{postId}")
    @Operation(
        summary = "게시글 수정",
        responses = {
            @ApiResponse(responseCode = "200", description = "게시글 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "JWT 인증 실패"),
            @ApiResponse(responseCode = "403", description = "작성자가 아님")
        }
    )
    public ResponseEntity<String> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto requestDto,
            Authentication authentication) {

        String username = authentication.getName();
        postService.updatePost(postId, username, requestDto);

        return ResponseEntity.ok("게시글 수정 성공");
    }

    @DeleteMapping("/{postId}")
    @Operation(
        summary = "게시글 삭제",
        responses = {
            @ApiResponse(responseCode = "204", description = "게시글 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "JWT 인증 실패"),
            @ApiResponse(responseCode = "403", description = "작성자가 아님")
        }
    )
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            Authentication authentication) {

        String username = authentication.getName();
        postService.deletePost(postId, username);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(
        summary = "게시글 목록 조회 (페이징, 정렬)",
        responses = {
            @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
        }
    )
    public ResponseEntity<Page<PostListResponseDto>> getAllPosts( // 페이징
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "postId") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Page<PostListResponseDto> posts = postService.getAllPosts(page, size, sortBy, direction);

        return ResponseEntity.ok(posts);
    }

}