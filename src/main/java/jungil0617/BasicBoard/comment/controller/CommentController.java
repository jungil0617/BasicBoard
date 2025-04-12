package jungil0617.BasicBoard.comment.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jungil0617.BasicBoard.comment.dto.request.CommentRequestDto;
import jungil0617.BasicBoard.comment.dto.response.CommentResponseDto;
import jungil0617.BasicBoard.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
@Tag(name = "Comment Api")
public class CommentController implements CommentDocsController{

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequestDto requestDto,
            Authentication authentication) {
        String username = authentication.getName();
        commentService.createComment(username, postId, requestDto.comment());
        return ResponseEntity.status(201).body("댓글 작성 성공");
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long postId) {
        List<CommentResponseDto> comments = commentService.getComments(postId);
        return ResponseEntity.ok(comments);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<String> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequestDto requestDto,
            Authentication authentication) {
        String username = authentication.getName();
        commentService.updateComment(commentId, username, requestDto.comment());
        return ResponseEntity.ok("댓글 수정 성공");
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            Authentication authentication) {
        String username = authentication.getName();
        commentService.deleteComment(commentId, username);
        return ResponseEntity.noContent().build();
    }

}