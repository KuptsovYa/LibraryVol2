package com.example.LibraryVol2.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "librarydb")
public class UsersEntity {
    private int idusers;
    private String login;
    private String password;
    private PersonalEntity personalByIdusers;
    private RolesEntity rolesByIdusers;

    @Id
    @Column(name = "idusers", nullable = false)
    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 45)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 256)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return idusers == that.idusers &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idusers, login, password);
    }

    @OneToOne(mappedBy = "usersByUsersIdusers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public PersonalEntity getPersonalByIdusers() {
        return personalByIdusers;
    }

    public void setPersonalByIdusers(PersonalEntity personalByIdusers) {
        this.personalByIdusers = personalByIdusers;
    }

    @OneToOne(mappedBy = "usersByUsersIdusers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public RolesEntity getRolesByIdusers() {
        return rolesByIdusers;
    }

    public void setRolesByIdusers(RolesEntity rolesByIdusers) {
        this.rolesByIdusers = rolesByIdusers;
    }
}
