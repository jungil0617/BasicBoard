package jungil0617.BasicBoard.likes.service;

import jungil0617.BasicBoard.likes.entity.Like;
import jungil0617.BasicBoard.likes.repository.LikeRepository;
import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.post.repository.PostRepository;
import jungil0617.BasicBoard.user.entity.User;
import jungil0617.BasicBoard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // 좋아요 토글
    @Transactional
    public boolean like(String username, Long postId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        return likeRepository.findByUserAndPost(user, post)
                .map(like -> {likeRepository.delete(like); return false;})
                .orElseGet(() -> {likeRepository.save(new Like(user, post)); return true;});
    }

    @Transactional(readOnly = true)
    public Long getLikeCount(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        return likeRepository.countByPost(post);
    }

}