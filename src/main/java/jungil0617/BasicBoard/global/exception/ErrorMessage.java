package jungil0617.BasicBoard.global.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    DUPLICATE_USERNAME("이미 존재하는 사용자명입니다."),
    USER_NOT_FOUND("존재하지 않는 사용자입니다."),
    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다."),
    INVALID_REFRESH_TOKEN("유효하지 않은 리프레시 토큰입니다."),

    POST_NOT_FOUND("존재하지 않는 게시글입니다."),
    POST_UNAUTHORIZED_ACCESS("게시글에 대한 권한이 없습니다."),

    COMMENT_NOT_FOUND("존재하지 않는 댓글입니다."),
    COMMENT_UNAUTHORIZED_ACCESS("댓글에 대한 권한이 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}