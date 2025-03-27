package jungil0617.BasicBoard.likes.repository;

import jungil0617.BasicBoard.likes.entity.Like;
import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    // 해당 게시글에 좋아요를 눌렀는지 확인
    Optional<Like> findByUserAndPost(User user, Post post);

    Long countByPost(Post post);

}