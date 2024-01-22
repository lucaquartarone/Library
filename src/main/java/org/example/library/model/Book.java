package org.example.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(schema = "book")
public class Book {
    @Size(min = 2)
    private String title, author;

    @NotNull
    @Min(0)
    private Integer yearOfPublish;

    @NotNull
    @DecimalMin("1.0")
    private Double price;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYearOfPublish() {
        return yearOfPublish;
    }

    public void setYearOfPublish(int yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Book() {
    }

    public Book(String title, String author, Integer yearOfPublish, Double price) {
        this.title = title;
        this.author = author;
        this.yearOfPublish = yearOfPublish;
        this.price = price;
    }
}
