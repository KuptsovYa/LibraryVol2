package com.example.LibraryVol2.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusers;

    private String login;
    private String password;
    private String UUId;

    public String getUUId() { return UUId; }

    public int getIdusers() { return idusers; }

    public void setIdusers(int idusers) { this.idusers = idusers; }

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
        return "User{" +
                "idusers=" + idusers +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
