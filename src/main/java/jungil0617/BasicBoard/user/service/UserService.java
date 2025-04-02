package jungil0617.BasicBoard.user.service;

import jungil0617.BasicBoard.global.jwt.JwtTokenProvider;
import jungil0617.BasicBoard.user.dto.TokenResponse;
import jungil0617.BasicBoard.user.dto.UserLoginRequestDto;
import jungil0617.BasicBoard.user.dto.UserSignupRequestDto;
import jungil0617.BasicBoard.user.entity.User;
import jungil0617.BasicBoard.user.exception.UserValidator;
import jungil0617.BasicBoard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserValidator userValidator;

    @Transactional
    public void signup(UserSignupRequestDto requestDto) {
        userValidator.validateDuplicateUsername(requestDto.getUsername());

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(
                requestDto.getUsername(),
                encodedPassword,
                requestDto.getNickname()
        );

        userRepository.save(user);
    }

    @Transactional
    public TokenResponse login(UserLoginRequestDto requestDto) {
        User user = userValidator.validateUserExists(requestDto.getUsername());
        userValidator.validatePassword(requestDto.getPassword(), user.getPassword());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getUsername());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUsername());

        user.updateRefreshToken(refreshToken);
        userRepository.save(user);

        return new TokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public void updateNickname(String username, String newNickname) {
        User user = userValidator.validateUserExists(username);
        user.updateNickname(newNickname);
    }

    @Transactional
    public void logout(String username) {
        User user = userValidator.validateUserExists(username);

        user.updateRefreshToken(null);
        userRepository.save(user);
    }

}