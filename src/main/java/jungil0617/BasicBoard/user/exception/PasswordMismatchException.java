package jungil0617.BasicBoard.user.exception;

import jungil0617.BasicBoard.global.exception.CustomException;
import jungil0617.BasicBoard.global.exception.ErrorMessage;

public class PasswordMismatchException extends CustomException {

    public PasswordMismatchException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}