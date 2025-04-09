package jungil0617.BasicBoard.saves.service;

import jungil0617.BasicBoard.post.dto.PostListResponseDto;
import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.post.exception.PostNotFoundException;
import jungil0617.BasicBoard.post.repository.PostRepository;
import jungil0617.BasicBoard.saves.entity.Save;
import jungil0617.BasicBoard.saves.repository.SaveRepository;
import jungil0617.BasicBoard.user.entity.User;
import jungil0617.BasicBoard.user.exception.UserNotFoundException;
import jungil0617.BasicBoard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static jungil0617.BasicBoard.global.exception.ErrorMessage.POST_NOT_FOUND;
import static jungil0617.BasicBoard.global.exception.ErrorMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SaveService {

    private final SaveRepository saveRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // 저장 토글
    @Transactional
    public boolean save(String username, Long postId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));

        return saveRepository.findByUserAndPost(user, post)
                .map(save -> {saveRepository.delete(save); return false; })
                .orElseGet(() -> { saveRepository.save(new Save(user, post)); return true; });
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> getSavedPosts(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        List<Save> saves = saveRepository.findAllByUser(user);

        return saves.stream()
                .map(save -> PostListResponseDto.fromEntity(save.getPost()))
                .toList();
    }

}