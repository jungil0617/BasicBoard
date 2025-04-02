package jungil0617.BasicBoard.comment.exception;

public class CommentException extends RuntimeException {

    public CommentException(CommentErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

}