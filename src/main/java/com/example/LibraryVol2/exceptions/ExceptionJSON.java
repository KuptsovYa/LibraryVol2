package com.example.LibraryVol2.exceptions;

public class ExceptionJSON {

    private String url;
    private String message;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionJSON(String url, String message) {
        this.url = url;
        this.message = message;
    }
}
