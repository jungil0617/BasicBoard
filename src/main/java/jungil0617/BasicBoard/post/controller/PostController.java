package jungil0617.BasicBoard.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jungil0617.BasicBoard.post.dto.PostRequestDto;
import jungil0617.BasicBoard.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}