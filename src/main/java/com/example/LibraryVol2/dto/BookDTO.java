package com.example.LibraryVol2.dto;

public class BookDTO {

    private String author;
    private String title;
    private String content;
    private int unDesirable;

    public BookDTO() {
    }

    public BookDTO(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
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
        return "BookDTO{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
