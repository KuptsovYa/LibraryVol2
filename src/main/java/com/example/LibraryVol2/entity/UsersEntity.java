package com.example.LibraryVol2.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "librarydb", catalog = "")
public class UsersEntity {
    private int idusers;
    private String login;
    private String password;
    private Collection<BooksEntity> booksByIdusers;
    private PersonalEntity personalByIdusers;

    @Id
    @Column(name = "idusers")
    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
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

    @OneToMany(mappedBy = "usersByUsersIdusers")
    public Collection<BooksEntity> getBooksByIdusers() {
        return booksByIdusers;
    }

    public void setBooksByIdusers(Collection<BooksEntity> booksByIdusers) {
        this.booksByIdusers = booksByIdusers;
    }

    @OneToOne(mappedBy = "usersByUsersIdusers")
    public PersonalEntity getPersonalByIdusers() {
        return personalByIdusers;
    }

    public void setPersonalByIdusers(PersonalEntity personalByIdusers) {
        this.personalByIdusers = personalByIdusers;
    }
}
