package jungil0617.BasicBoard.likes.service;

import jungil0617.BasicBoard.likes.entity.Like;
import jungil0617.BasicBoard.likes.repository.LikeRepository;
import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.post.exception.PostNotFoundException;
import jungil0617.BasicBoard.post.repository.PostRepository;
import jungil0617.BasicBoard.user.entity.User;
import jungil0617.BasicBoard.user.exception.UserNotFoundException;
import jungil0617.BasicBoard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static jungil0617.BasicBoard.global.exception.ErrorMessage.POST_NOT_FOUND;
import static jungil0617.BasicBoard.global.exception.ErrorMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public boolean like(String username, Long postId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));

        return toggleLike(user, post);
    }

    private boolean toggleLike(User user, Post post) {
        return likeRepository.findByUserAndPost(user, post)
                .map(like -> { likeRepository.delete(like); return false; })
                .orElseGet(() -> {
                    Like like = Like.builder().user(user).post(post).build();
                    likeRepository.save(like);
                    return true;
                });
    }

    @Transactional(readOnly = true)
    public Long getLikeCount(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));
        return likeRepository.countByPost(post);
    }

}