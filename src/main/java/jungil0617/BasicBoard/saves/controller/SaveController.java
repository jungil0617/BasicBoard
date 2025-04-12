package jungil0617.BasicBoard.saves.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jungil0617.BasicBoard.post.dto.response.PostListResponseDto;
import jungil0617.BasicBoard.saves.service.SaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name = "Save Api")
public class SaveController implements SaveDocsController{

    private final SaveService saveService;

    @PostMapping("/{postId}/saves")
    public ResponseEntity<String> save(@PathVariable Long postId, Authentication authentication) {
        String username = authentication.getName();
        boolean isSaved = saveService.save(username, postId);

        String message = isSaved ? "저장" : "저장 취소";
        return ResponseEntity.ok(message);
    }

    @GetMapping("/saves")
    public ResponseEntity<List<PostListResponseDto>> getSavedPosts(Authentication authentication) {
        String username = authentication.getName();
        List<PostListResponseDto> savedPosts = saveService.getSavedPosts(username);
        return ResponseEntity.ok(savedPosts);
    }

}