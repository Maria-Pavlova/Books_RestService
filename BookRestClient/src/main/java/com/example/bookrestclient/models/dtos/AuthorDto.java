package com.example.bookrestclient.models.dtos;

import java.io.Serializable;


public class AuthorDto implements Serializable {

    private String name;


    public String getName() {
        return name;
    }


    public AuthorDto setName(String name) {
        this.name = name;
        return this;
    }
}
