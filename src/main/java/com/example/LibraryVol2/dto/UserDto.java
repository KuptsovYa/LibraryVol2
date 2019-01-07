package com.example.LibraryVol2.dto;

import java.util.Set;

public class UserDto {

    private String login;
    private String password;
    private Roles roles;

    public UserDto() {

    }

    public UserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
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
