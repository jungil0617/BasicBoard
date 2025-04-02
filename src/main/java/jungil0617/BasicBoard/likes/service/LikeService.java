package jungil0617.BasicBoard.likes.service;

import jungil0617.BasicBoard.likes.entity.Like;
import jungil0617.BasicBoard.likes.repository.LikeRepository;
import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.post.exception.PostValidator;
import jungil0617.BasicBoard.user.entity.User;
import jungil0617.BasicBoard.user.exception.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserValidator userValidator;
    private final PostValidator postValidator;

    // 좋아요 토글
    @Transactional
    public boolean like(String username, Long postId) {
        User user = userValidator.validateUserExists(username);
        Post post = postValidator.validatePostExists(postId);

        return likeRepository.findByUserAndPost(user, post)
                .map(like -> {likeRepository.delete(like); return false;})
                .orElseGet(() -> {likeRepository.save(new Like(user, post)); return true;});
    }

    @Transactional(readOnly = true)
    public Long getLikeCount(Long postId) {
        Post post = postValidator.validatePostExists(postId);
        return likeRepository.countByPost(post);
    }

}