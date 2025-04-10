package jungil0617.BasicBoard.post.service;

import jungil0617.BasicBoard.post.dto.PostListResponseDto;
import jungil0617.BasicBoard.post.dto.PostRequestDto;
import jungil0617.BasicBoard.post.dto.PostResponseDto;
import jungil0617.BasicBoard.post.entity.Post;
import jungil0617.BasicBoard.post.exception.PostNotFoundException;
import jungil0617.BasicBoard.post.exception.UnauthorizedPostAccessException;
import jungil0617.BasicBoard.post.repository.PostRepository;
import jungil0617.BasicBoard.user.entity.User;
import jungil0617.BasicBoard.user.exception.UserNotFoundException;
import jungil0617.BasicBoard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static jungil0617.BasicBoard.global.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createPost(String username, PostRequestDto requestDto) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        Post post = Post.builder()
                .user(user)
                .title(requestDto.title())
                .content(requestDto.content())
                .build();

        postRepository.save(post);
    }

    @Transactional
    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));

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

    @Transactional
    public void updatePost(Long postId, String username, PostRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));

        if (!post.getUser().getUsername().equals(username)) {
            throw new UnauthorizedPostAccessException(POST_UNAUTHORIZED_ACCESS);
        }

        post.update(requestDto.title(), requestDto.content());
    }

    @Transactional
    public void deletePost(Long postId, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));

        if (!post.getUser().getUsername().equals(username)) {
            throw new UnauthorizedPostAccessException(POST_UNAUTHORIZED_ACCESS);
        }

        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public Page<PostListResponseDto> getAllPosts(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return postRepository.findAll(pageable).map(PostListResponseDto::fromEntity);
    }

}