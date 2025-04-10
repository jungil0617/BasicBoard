package jungil0617.BasicBoard.saves.entity;

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
@Table(name = "saves", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "post_id"})})
@AllArgsConstructor
@Builder
public class Save {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "save_id")
    private Long saveId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id")
    private Post post;

}