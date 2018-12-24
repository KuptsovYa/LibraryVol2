package com.example.LibraryVol2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class BooksEntityPK implements Serializable {
    private int idbooks;
    private int usersIdusers;

    @Column(name = "idbooks")
    @Id
    public int getIdbooks() {
        return idbooks;
    }

    public void setIdbooks(int idbooks) {
        this.idbooks = idbooks;
    }

    @Column(name = "users_idusers", insertable=false, updatable=false)
    @Id
    public int getUsersIdusers() {
        return usersIdusers;
    }

    public void setUsersIdusers(int usersIdusers) {
        this.usersIdusers = usersIdusers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksEntityPK that = (BooksEntityPK) o;
        return idbooks == that.idbooks &&
                usersIdusers == that.usersIdusers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idbooks, usersIdusers);
    }
}
