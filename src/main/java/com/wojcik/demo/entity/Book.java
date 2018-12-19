package com.wojcik.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    @Size(min=1, max=150)
    @NotNull(message = "required")
    private String author;

    @Column
    @Size(min=1, max=200)
    @NotNull(message = "required")
    private String title;

    @Column
    @Max(2018)
    private int year;

    @Column
    private String description;

    public Book() {
    }

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Book(String author, String title, int year, String description) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                '}';
    }
}
