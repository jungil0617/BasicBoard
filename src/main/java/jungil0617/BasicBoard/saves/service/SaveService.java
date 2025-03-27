package jungil0617.BasicBoard.saves.service;

import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.post.repository.PostRepository;
import jungil0617.BasicBoard.saves.entity.Save;
import jungil0617.BasicBoard.saves.repository.SaveRepository;
import jungil0617.BasicBoard.user.entity.User;
import jungil0617.BasicBoard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        return saveRepository.findByUserAndPost(user, post)
                .map(save -> {saveRepository.delete(save); return false; })
                .orElseGet(() -> { saveRepository.save(new Save(user, post)); return true; });
    }

}