package jungil0617.BasicBoard.global.exception;

public class CustomException extends RuntimeException {

    public CustomException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

}