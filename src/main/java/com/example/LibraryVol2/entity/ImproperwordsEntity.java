package com.example.LibraryVol2.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "improperwords", schema = "librarydb", catalog = "")
public class ImproperwordsEntity {
    private int idimproperWords;
    private String word;

    @Id
    @Column(name = "idimproperWords", nullable = false)
    public int getIdimproperWords() {
        return idimproperWords;
    }

    public void setIdimproperWords(int idimproperWords) {
        this.idimproperWords = idimproperWords;
    }

    @Basic
    @Column(name = "word", nullable = false, length = 45)
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImproperwordsEntity that = (ImproperwordsEntity) o;
        return idimproperWords == that.idimproperWords &&
                Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idimproperWords, word);
    }
}
