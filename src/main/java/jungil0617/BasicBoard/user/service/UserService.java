package jungil0617.BasicBoard.user.service;

import jungil0617.BasicBoard.global.jwt.JwtTokenProvider;
import jungil0617.BasicBoard.user.dto.response.TokenResponse;
import jungil0617.BasicBoard.user.dto.request.UserLoginRequestDto;
import jungil0617.BasicBoard.user.dto.request.UserSignupRequestDto;
import jungil0617.BasicBoard.user.entity.User;
import jungil0617.BasicBoard.user.exception.*;
import jungil0617.BasicBoard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static jungil0617.BasicBoard.global.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void signup(UserSignupRequestDto requestDto) {
        if (userRepository.findByUsername(requestDto.username()).isPresent()) {
            throw new DuplicateUsernameException(DUPLICATE_USERNAME);
        }

        String encodedPassword = passwordEncoder.encode(requestDto.password());

        User user = User.builder()
                .username(requestDto.username())
                .password(encodedPassword)
                .nickname(requestDto.nickname())
                .build();

        userRepository.save(user);
    }

    @Transactional
    public TokenResponse login(UserLoginRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.username())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        if (!passwordEncoder.matches(requestDto.password(), user.getPassword())) {
            throw new PasswordMismatchException(PASSWORD_MISMATCH);
        }

        user.validatePassword(requestDto.password());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getUsername());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUsername());

        user.updateRefreshToken(refreshToken);
        userRepository.save(user);

        return new TokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public void updateNickname(String username, String newNickname) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        user.updateNickname(newNickname);
    }

    @Transactional
    public void logout(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        user.updateRefreshToken(null);
        userRepository.save(user);
    }

    @Transactional
    public String reissueAccessToken(String refreshToken) {
        String username = jwtTokenProvider.getUsernameFromToken(refreshToken);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        if (!refreshToken.equals(user.getRefreshToken())) {
            throw new InvalidRefreshTokenException(INVALID_REFRESH_TOKEN);
        }

        return jwtTokenProvider.generateAccessToken(username);
    }

}