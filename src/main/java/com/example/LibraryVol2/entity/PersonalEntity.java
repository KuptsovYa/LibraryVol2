package com.example.LibraryVol2.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "personal", schema = "librarydb", catalog = "")
public class PersonalEntity {
    private String firstName;
    private String lastName;
    private String patronicName;
    private int usersIdusers;
    private UsersEntity usersByUsersIdusers;

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "patronicName")
    public String getPatronicName() {
        return patronicName;
    }

    public void setPatronicName(String patronicName) {
        this.patronicName = patronicName;
    }

    @Id
    @Column(name = "users_idusers")
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
        PersonalEntity that = (PersonalEntity) o;
        return usersIdusers == that.usersIdusers &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(patronicName, that.patronicName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronicName, usersIdusers);
    }

    @OneToOne
    @JoinColumn(name = "users_idusers", referencedColumnName = "idusers", nullable = false)
    public UsersEntity getUsersByUsersIdusers() {
        return usersByUsersIdusers;
    }

    public void setUsersByUsersIdusers(UsersEntity usersByUsersIdusers) {
        this.usersByUsersIdusers = usersByUsersIdusers;
    }
}
