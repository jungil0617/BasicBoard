package jungil0617.BasicBoard.comment.exception;

import jungil0617.BasicBoard.global.exception.CustomException;
import jungil0617.BasicBoard.global.exception.ErrorMessage;

public class UnauthorizedCommentAccessException extends CustomException {

    public UnauthorizedCommentAccessException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}