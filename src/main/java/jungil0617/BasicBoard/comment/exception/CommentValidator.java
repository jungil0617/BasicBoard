package jungil0617.BasicBoard.comment.exception;

import jungil0617.BasicBoard.comment.entity.Comment;
import jungil0617.BasicBoard.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static jungil0617.BasicBoard.comment.exception.CommentErrorMessage.*;

@Component
@RequiredArgsConstructor
public class CommentValidator {

    private final CommentRepository commentRepository;

    public Comment validateCommentExists(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException(COMMENT_NOT_FOUND));
    }

    public void validateCommentOwner(Comment comment, String username) {
        if (!comment.getUser().getUsername().equals(username)) {
            throw new CommentException(UNAUTHORIZED_ACCESS);
        }
    }

}