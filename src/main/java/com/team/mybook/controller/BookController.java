package com.team.mybook.controller;

import com.team.mybook.data.entity.Book;
import com.team.mybook.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path="/api/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewBook (@RequestBody Book requestBook){
        Book book = new Book(requestBook.getTitle(), requestBook.getAuthor(), requestBook.getPublisher(),
                            requestBook.getPages(), requestBook.getGenre(), requestBook.getDescription());
        bookRepository.save(book);
    }

    @GetMapping(path="/{bookTitle}")
    public @ResponseBody Book getBookByTitle(HttpServletResponse response, @PathVariable String bookTitle) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping(path="/id/{bookID}")
    public @ResponseBody Book getBookById(HttpServletResponse response, @PathVariable long bookID) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return bookRepository.findById(bookID).get();
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Book> getAllBooks(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return bookRepository.findAll();
    }

    @DeleteMapping("/delete/{bookID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable long bookID) {
        bookRepository.deleteById(bookID);
    }
}
