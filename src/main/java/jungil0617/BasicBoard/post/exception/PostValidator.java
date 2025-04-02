package jungil0617.BasicBoard.post.exception;

import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static jungil0617.BasicBoard.post.exception.PostErrorMessage.*;

@Component
@RequiredArgsConstructor
public class PostValidator {

    private final PostRepository postRepository;

    public Post validatePostExists(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostException(POST_NOT_FOUND));
    }

    public void validatePostOwner(Post post, String currentUsername) {
        if (!post.getUser().getUsername().equals(currentUsername)) {
            throw new PostException(UNAUTHORIZED_ACCESS);
        }
    }

}