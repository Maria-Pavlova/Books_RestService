package com.example.bookrestclient.models.dtos;

import java.io.Serializable;


public class BookDto implements Serializable {

    private Long id;
    private String title;
    private String isbn;
    private AuthorDto author;


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public BookDto setId(Long id) {
        this.id = id;
        return this;
    }

    public BookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookDto setAuthor(AuthorDto author) {
        this.author = author;
        return this;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author=" + (author != null ? author.getName() : null) +
                '}';
    }
}
