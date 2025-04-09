package jungil0617.BasicBoard.user.exception;

import jungil0617.BasicBoard.global.exception.CustomException;
import jungil0617.BasicBoard.global.exception.ErrorMessage;

public class DuplicateUsernameException extends CustomException {

    public DuplicateUsernameException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}