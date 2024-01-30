package model;

public class BoardPost {
    private String memName;
    private String memId;
    private String boardSubject;
    private String boardContent;

    public BoardPost() {
    }

    public BoardPost(String memName, String memId, String boardSubject, String boardContent) {
        this.memName = memName;
        this.memId = memId;
        this.boardSubject = boardSubject;
        this.boardContent = boardContent;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getBoardSubject() {
        return boardSubject;
    }

    public void setBoardSubject(String boardSubject) {
        this.boardSubject = boardSubject;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }
}
