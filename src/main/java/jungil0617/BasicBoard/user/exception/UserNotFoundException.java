package jungil0617.BasicBoard.user.exception;

import jungil0617.BasicBoard.global.exception.CustomException;
import jungil0617.BasicBoard.global.exception.ErrorMessage;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}