package com.team.mybook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.mybook.data.entity.Book;
import com.team.mybook.data.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void test_get_all_books_success() throws Exception {
        List<Book> books = Arrays.asList(
                new Book("Testowa1", "AutorTest", "WydawcaTest",
                        100, "Fantasy", "Test"),
                new Book("Testowa2", "AutorTest", "WydawcaTest",
                        100, "Fantasy", "Test"));
        when(bookRepository.findAll()).thenReturn(books);
        this.mockMvc.perform(get("/api/book/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Testowa1")))
                .andExpect(jsonPath("$[0].author", is("AutorTest")))
                .andExpect(jsonPath("$[0].publisher", is("WydawcaTest")))
                .andExpect(jsonPath("$[0].pages", is(100)))
                .andExpect(jsonPath("$[0].genre", is("Fantasy")))
                .andExpect(jsonPath("$[0].description", is("Test")))

                .andExpect(jsonPath("$[1].title", is("Testowa2")))
                .andExpect(jsonPath("$[1].author", is("AutorTest")))
                .andExpect(jsonPath("$[1].publisher", is("WydawcaTest")))
                .andExpect(jsonPath("$[1].pages", is(100)))
                .andExpect(jsonPath("$[1].genre", is("Fantasy")))
                .andExpect(jsonPath("$[1].description", is("Test")));

        verify(bookRepository, times(1)).findAll();
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void test_get_all_books_not_found() throws Exception {
        this.mockMvc.perform(get("/api/books/all"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_get_by_title_success() throws Exception {
        Book book = new Book("Testowa1", "AutorTest", "WydawcaTest",
                100, "Fantasy", "Test");

        when(bookRepository.findByTitle("Testowa1")).thenReturn(book);

        mockMvc.perform(get("/api/book/{title}", "Testowa1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.title", is("Testowa1")))
                .andExpect(jsonPath("$.author", is("AutorTest")))
                .andExpect(jsonPath("$.publisher", is("WydawcaTest")))
                .andExpect(jsonPath("$.pages", is(100)))
                .andExpect(jsonPath("$.genre", is("Fantasy")))
                .andExpect(jsonPath("$.description", is("Test")));

        verify(bookRepository, times(1)).findByTitle("Testowa1");
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void test_get_by_title_null() throws Exception {
        when(bookRepository.findByTitle("Testowa1")).thenReturn(null);

        mockMvc.perform(get("/api/book/{title}", "Testowa1"))
                .andExpect(status().isOk());
        verify(bookRepository, times(1)).findByTitle("Testowa1");
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void test_create_and_delete_book_success() throws Exception {
        Book book = new Book("Testowa1", "AutorTest", "WydawcaTest",
                100, "Fantasy", "Test");

        when(bookRepository.save(book)).thenReturn(book);
        doNothing().when(bookRepository).deleteBookByTitle(book.getTitle());

        mockMvc.perform(
                post("/api/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(book)))
                .andExpect(status().isCreated());
        mockMvc.perform(
                delete("/api/book/delete/{bookTitle}", book.getTitle()))
                .andExpect(status().isOk());
    }


    @Test
    public void test_delete_book_fail_not_found() throws Exception {
        Book book = new Book("Testowa1", "AutorTest", "WydawcaTest",
                100, "Fantasy", "Test");

        when(bookRepository.findByTitle(book.getTitle())).thenReturn(null);

        mockMvc.perform(
                delete("/api/book/delete/{title}", book.getTitle()))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
