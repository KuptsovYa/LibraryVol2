package com.example.LibraryVol2.dto;

public class ConfigDto {

    private int page;
    private String userName;
    private boolean personal;
    private Long userId;

    public ConfigDto(int page, boolean personal) {
        this.page = page;
        this.personal = personal;
    }

    public ConfigDto(int page) {
        this.page = page;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ConfigDto() {
    }

    public boolean isPersonal() {
        return personal;
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ConfigDto{" +
                "page=" + page +
                ", userName='" + userName + '\'' +
                ", personal=" + personal +
                ", userId=" + userId +
                '}';
    }
}
