package jungil0617.BasicBoard.user.exception;

import jungil0617.BasicBoard.global.exception.CustomException;
import jungil0617.BasicBoard.global.exception.ErrorMessage;

public class InvalidRefreshTokenException extends CustomException {

    public InvalidRefreshTokenException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}