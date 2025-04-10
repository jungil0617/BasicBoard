package jungil0617.BasicBoard.likes.entity;

import jakarta.persistence.*;
import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "likes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "post_id"})})
@AllArgsConstructor
@Builder
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id")
    private Post post;

}