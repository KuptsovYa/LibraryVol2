package com.example.LibraryVol2.dto;

public class BookDto {

    private Integer bookId;
    private String author;
    private String title;
    private String content;
    private String userName;
    private Long userId;
    private int unDesirable;

    public BookDto() {
    }

    public BookDto(String author, String title, String content, String userName) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.userName = userName;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUnDesirable() {
        return unDesirable;
    }

    public void setUnDesirable(int unDesirable) {
        this.unDesirable = unDesirable;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
