package jungil0617.BasicBoard.saves.repository;

import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.saves.entity.Save;
import jungil0617.BasicBoard.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaveRepository extends JpaRepository<Save, Long> {

    Optional<Save> findByUserAndPost(User user, Post post);

}