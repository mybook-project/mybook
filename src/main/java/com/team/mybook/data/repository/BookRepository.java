package com.team.mybook.data.repository;

import com.team.mybook.data.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
