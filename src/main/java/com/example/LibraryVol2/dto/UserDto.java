package com.example.LibraryVol2.dto;

public class UserDto {

    private String login;
    private String password;
    private RoleDto roles;

    public UserDto() {

    }

    public UserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public RoleDto getRoles() {
        return roles;
    }

    public void setRoles(RoleDto roles) {
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
        return "UserDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
