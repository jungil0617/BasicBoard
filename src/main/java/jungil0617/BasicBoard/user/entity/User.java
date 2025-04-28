package jungil0617.BasicBoard.user.entity;

import jakarta.persistence.*;
import jungil0617.BasicBoard.global.exception.ErrorMessage;
import jungil0617.BasicBoard.user.exception.PasswordMismatchException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Table(name = "users")
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "refresh_token")
    private String refreshToken;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void validatePassword(String password) {
        if (!this.password.equals(password)) {
            throw new PasswordMismatchException(ErrorMessage.PASSWORD_MISMATCH);
        }
    }

}