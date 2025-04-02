package jungil0617.BasicBoard.user.exception;

import jungil0617.BasicBoard.user.entity.User;
import jungil0617.BasicBoard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static jungil0617.BasicBoard.user.exception.UserErrorMessage.*;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validateDuplicateUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserException(DUPLICATE_USERNAME);
        }
    }

    public User validateUserExists(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));
    }

    public void validatePassword(String rawPassword, String encodedPassword) {
        if (!org.springframework.security.crypto.bcrypt.BCrypt.checkpw(rawPassword, encodedPassword)) {
            throw new UserException(PASSWORD_MISMATCH);
        }
    }

}