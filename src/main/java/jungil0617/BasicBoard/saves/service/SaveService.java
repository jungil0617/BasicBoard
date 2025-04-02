package jungil0617.BasicBoard.saves.service;

import jungil0617.BasicBoard.post.dto.PostListResponseDto;
import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.post.exception.PostValidator;
import jungil0617.BasicBoard.saves.entity.Save;
import jungil0617.BasicBoard.saves.repository.SaveRepository;
import jungil0617.BasicBoard.user.entity.User;
import jungil0617.BasicBoard.user.exception.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveService {

    private final SaveRepository saveRepository;
    private final UserValidator userValidator;
    private final PostValidator postValidator;

    // 저장 토글
    @Transactional
    public boolean save(String username, Long postId) {
        User user = userValidator.validateUserExists(username);
        Post post = postValidator.validatePostExists(postId);

        return saveRepository.findByUserAndPost(user, post)
                .map(save -> {saveRepository.delete(save); return false; })
                .orElseGet(() -> { saveRepository.save(new Save(user, post)); return true; });
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> getSavedPosts(String username) {
        User user = userValidator.validateUserExists(username);

        List<Save> saves = saveRepository.findAllByUser(user);

        return saves.stream()
                .map(save -> PostListResponseDto.fromEntity(save.getPost()))
                .toList();
    }

}