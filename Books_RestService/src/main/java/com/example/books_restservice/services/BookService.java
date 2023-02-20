package com.example.books_restservice.services;

import com.example.books_restservice.models.dtos.AuthorDto;
import com.example.books_restservice.models.dtos.BookDto;
import com.example.books_restservice.models.entities.Author;
import com.example.books_restservice.models.entities.Book;
import com.example.books_restservice.repositories.AuthorRepository;
import com.example.books_restservice.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookDto> getAllBooks() {
       return bookRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    private BookDto map(Book book){
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName(book.getAuthor().getName());

        return new BookDto()
                .setId(book.getId())
                .setAuthor(authorDto)
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle());

    }

    public Optional<BookDto> getBookById(Long id) {
      return   bookRepository.findById(id)
                .map(this::map);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public long createBook(BookDto bookDto) {

        String authorName = bookDto.getAuthor().getName();
        Optional<Author> author = authorRepository.findByName(authorName);

        Book book = new Book()
                .setTitle(bookDto.getTitle())
                .setIsbn(bookDto.getIsbn())
                .setAuthor(author.orElseGet(() -> createAuthor(authorName)));
         return bookRepository.save(book).getId();
    }

    private Author createAuthor(String authorName){
         return authorRepository.save(new Author().setName(authorName));


    }
}
