package jungil0617.BasicBoard.post.exception;

import jungil0617.BasicBoard.global.exception.CustomException;
import jungil0617.BasicBoard.global.exception.ErrorMessage;

public class UnauthorizedPostAccessException extends CustomException {

    public UnauthorizedPostAccessException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}