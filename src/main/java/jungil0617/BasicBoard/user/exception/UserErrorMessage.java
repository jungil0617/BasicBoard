package jungil0617.BasicBoard.user.exception;

public enum UserErrorMessage {

    DUPLICATE_USERNAME("이미 존재하는 사용자명입니다."),
    USER_NOT_FOUND("존재하지 않는 사용자입니다."),
    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다.");

    private final String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}