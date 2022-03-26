package com.ductlmse.learning_guru_spring.repositories.dao.impl;

import com.ductlmse.learning_guru_spring.domain.Book;
import com.ductlmse.learning_guru_spring.repositories.BookJdbcDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ComponentScan(basePackages = "com.ductlmse.learning_guru_spring.repositories")
class BookJdbcDaoImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;
    BookJdbcDao dao;

    @BeforeEach
    void setUp() {
        dao = new BookJdbcDaoImpl(jdbcTemplate);
    }

    @Test
    void findAllBook() {
        var allBook = dao.findAllBook(1L);
        allBook.ifPresent(System.out::println);
    }
}