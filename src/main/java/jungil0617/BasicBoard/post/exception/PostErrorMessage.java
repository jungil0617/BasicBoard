package jungil0617.BasicBoard.post.exception;

public enum PostErrorMessage {

    POST_NOT_FOUND("존재하지 않는 게시글입니다."),
    UNAUTHORIZED_ACCESS("게시글에 대한 권한이 없습니다.");

    private final String message;

    PostErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}