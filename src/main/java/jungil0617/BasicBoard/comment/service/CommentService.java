package jungil0617.BasicBoard.comment.service;

import jungil0617.BasicBoard.comment.dto.response.CommentResponseDto;
import jungil0617.BasicBoard.comment.entity.Comment;
import jungil0617.BasicBoard.comment.exception.CommentNotFoundException;
import jungil0617.BasicBoard.comment.exception.UnauthorizedCommentAccessException;
import jungil0617.BasicBoard.comment.repository.CommentRepository;
import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.post.exception.PostNotFoundException;
import jungil0617.BasicBoard.post.repository.PostRepository;
import jungil0617.BasicBoard.user.entity.User;
import jungil0617.BasicBoard.user.exception.UserNotFoundException;
import jungil0617.BasicBoard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static jungil0617.BasicBoard.global.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createComment(String username, Long postId, String content) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));

        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content(content)
                .build();

        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComments(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));

        return commentRepository.findByPost(post).stream()
                .map(CommentResponseDto::fromEntity)
                .toList();
    }

    @Transactional
    public void updateComment(Long commentId, String username, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND));

        if (!comment.getUser().getUsername().equals(username)) {
            throw new UnauthorizedCommentAccessException(COMMENT_UNAUTHORIZED_ACCESS);
        }

        comment.update(content);
    }

    @Transactional
    public void deleteComment(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND));

        if (!comment.getUser().getUsername().equals(username)) {
            throw new UnauthorizedCommentAccessException(COMMENT_UNAUTHORIZED_ACCESS);
        }

        commentRepository.delete(comment);
    }

}