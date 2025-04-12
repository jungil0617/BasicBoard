package jungil0617.BasicBoard.post.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jungil0617.BasicBoard.post.dto.response.PostListResponseDto;
import jungil0617.BasicBoard.post.dto.request.PostRequestDto;
import jungil0617.BasicBoard.post.dto.response.PostResponseDto;
import jungil0617.BasicBoard.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name = "Post Api")
public class PostController implements PostDocsController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> createPost(@Valid @RequestBody PostRequestDto requestDto, Authentication authentication) {
        String username = authentication.getName();
        postService.createPost(username, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("게시글 작성 성공");
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto postResponseDto = postService.getPost(postId);
        return ResponseEntity.ok(postResponseDto);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId, @Valid @RequestBody PostRequestDto requestDto, Authentication authentication) {
        String username = authentication.getName();
        postService.updatePost(postId, username, requestDto);

        return ResponseEntity.ok("게시글 수정 성공");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, Authentication authentication) {
        String username = authentication.getName();
        postService.deletePost(postId, username);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<PostListResponseDto>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "postId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Page<PostListResponseDto> posts = postService.getAllPosts(page, size, sortBy, direction);

        return ResponseEntity.ok(posts);
    }

}