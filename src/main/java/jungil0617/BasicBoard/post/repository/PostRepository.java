package jungil0617.BasicBoard.post.repository;

import jungil0617.BasicBoard.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}