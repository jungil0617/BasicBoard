package jungil0617.BasicBoard.comment.exception;

public enum CommentErrorMessage {

    COMMENT_NOT_FOUND("존재하지 않는 댓글입니다."),
    UNAUTHORIZED_ACCESS("댓글에 대한 권한이 없습니다.");

    private final String message;

    CommentErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}