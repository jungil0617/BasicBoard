package jungil0617.BasicBoard.comment.repository;

import jungil0617.BasicBoard.comment.entity.Comment;
import jungil0617.BasicBoard.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);

}