package com.example.bookrestclient;

import com.example.bookrestclient.models.dtos.BookDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;




@Component
public class BookRest implements CommandLineRunner {

    private static final String API_URL = "http://localhost:8080/api/books";
    public static final Logger LOGGER =  LoggerFactory.getLogger(BookRest.API_URL);

    private final RestTemplate restTemplate;

    public BookRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {


        ResponseEntity<BookDto[]> allBooksResponse = restTemplate.getForEntity(API_URL, BookDto[].class);

        if (allBooksResponse.hasBody()){
            BookDto[] books = allBooksResponse.getBody();

            for (BookDto book : books) {
                
                LOGGER.info("Retrieved a book: {}", book);
            }
        }
    }
}
