package jungil0617.BasicBoard.post.service;

import jungil0617.BasicBoard.post.dto.PostRequestDto;
import jungil0617.BasicBoard.post.dto.PostResponseDto;
import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.post.repository.PostRepository;
import jungil0617.BasicBoard.user.entity.User;
import jungil0617.BasicBoard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createPost(String username, PostRequestDto requestDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        Post post = new Post(user, requestDto.getTitle(), requestDto.getContent());
        postRepository.save(post);
    }

    @Transactional
    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        post.increaseViewCount();

        return new PostResponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getNickname(),
                post.getViewCount(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

}