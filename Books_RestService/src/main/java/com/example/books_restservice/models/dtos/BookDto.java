package com.example.books_restservice.models.dtos;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class BookDto implements Serializable {
    private Long id;
    private String title;
    private String isbn;
    private AuthorDto author;

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
}
