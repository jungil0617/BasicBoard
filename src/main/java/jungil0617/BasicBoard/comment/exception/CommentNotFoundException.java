package jungil0617.BasicBoard.comment.exception;

import jungil0617.BasicBoard.global.exception.CustomException;
import jungil0617.BasicBoard.global.exception.ErrorMessage;

public class CommentNotFoundException extends CustomException {

    public CommentNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}