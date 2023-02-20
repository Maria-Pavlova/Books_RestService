package com.example.books_restservice.web.controllers;

import com.example.books_restservice.models.dtos.BookDto;
import com.example.books_restservice.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity
                .ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id){

        Optional<BookDto> book = bookService.getBookById(id);

        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBookById(@PathVariable Long id){
        bookService.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto,
                                              UriComponentsBuilder uriComponentsBuilder){
        long bookId = bookService.createBook(bookDto);

       return ResponseEntity
                .created(uriComponentsBuilder.path("/api/books/{id}").build(bookId))
               .build();
    }
}
