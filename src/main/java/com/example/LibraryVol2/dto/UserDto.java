package com.example.LibraryVol2.dto;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

public class UserDto {

    private String login;
    private String password;

    public UserDto() {

    }

    public UserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
