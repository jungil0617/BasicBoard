package jungil0617.BasicBoard.user.exception;

public class UserException extends RuntimeException {

    public UserException(UserErrorMessage userErrorMessage) {
        super(userErrorMessage.getMessage());
    }

}