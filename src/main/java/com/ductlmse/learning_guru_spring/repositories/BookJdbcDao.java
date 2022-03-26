package com.ductlmse.learning_guru_spring.repositories;

import com.ductlmse.learning_guru_spring.domain.Book;

import java.util.Optional;

public interface BookJdbcDao {
    Optional<Book> findAllBook(Long id);
}
