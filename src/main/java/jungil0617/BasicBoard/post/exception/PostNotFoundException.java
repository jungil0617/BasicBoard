package jungil0617.BasicBoard.post.exception;

import jungil0617.BasicBoard.global.exception.CustomException;
import jungil0617.BasicBoard.global.exception.ErrorMessage;

public class PostNotFoundException extends CustomException {

    public PostNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}