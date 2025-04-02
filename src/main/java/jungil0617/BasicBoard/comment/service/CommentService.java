package jungil0617.BasicBoard.comment.service;

import jungil0617.BasicBoard.comment.dto.CommentResponseDto;
import jungil0617.BasicBoard.comment.entity.Comment;
import jungil0617.BasicBoard.comment.exception.CommentValidator;
import jungil0617.BasicBoard.comment.repository.CommentRepository;
import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.post.exception.PostValidator;
import jungil0617.BasicBoard.user.entity.User;
import jungil0617.BasicBoard.user.exception.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentValidator commentValidator;
    private final UserValidator userValidator;
    private final PostValidator postValidator;

    @Transactional
    public void createComment(String username, Long postId, String content) {
        User user = userValidator.validateUserExists(username);
        Post post = postValidator.validatePostExists(postId);

        Comment comment = new Comment(user, post, content);

        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComments(Long postId) {
        Post post = postValidator.validatePostExists(postId);

        return commentRepository.findByPost(post).stream()
                .map(CommentResponseDto::fromEntity)
                .toList();
    }

    @Transactional
    public void updateComment(Long commentId, String username, String content) {
        Comment comment = commentValidator.validateCommentExists(commentId);
        commentValidator.validateCommentOwner(comment, username);

        comment.update(content);
    }

    @Transactional
    public void deleteComment(Long commentId, String username) {
        Comment comment = commentValidator.validateCommentExists(commentId);
        commentValidator.validateCommentOwner(comment, username);

        commentRepository.delete(comment);
    }

}