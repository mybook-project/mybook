package com.team.mybook.controller;

import com.team.mybook.data.entity.Book;
import com.team.mybook.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/api/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewBook (@RequestParam String title, @RequestParam String author,
                                            @RequestParam String publisher, @RequestParam int pages,
                                            @RequestParam String genre , @RequestParam String description){
        Book book = new Book(title,author,publisher,pages,genre,description);
        bookRepository.save(book);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping(path="/delete")
    public @ResponseBody String deleteBook(@RequestParam long id) {
        bookRepository.deleteById(id);
        return "deleted";
    }
}
