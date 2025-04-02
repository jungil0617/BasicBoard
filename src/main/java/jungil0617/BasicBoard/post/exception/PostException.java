package jungil0617.BasicBoard.post.exception;

public class PostException extends RuntimeException {

    public PostException(PostErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

}