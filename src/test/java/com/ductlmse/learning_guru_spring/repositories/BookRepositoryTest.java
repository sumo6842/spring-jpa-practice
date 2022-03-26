package com.ductlmse.learning_guru_spring.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@ComponentScan(basePackages = "com.ductlmse.learning_guru_spring.repositories")
@AutoConfigureTestDatabase(replace = NONE)
class BookRepositoryTest {
    @Autowired
    AuthorRepositories authorRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findBookByTitle() {
//        authorRepository.findBookByTitle(NONE);
    }

    @Test
    void findBookByTitleNativeQuery() {
    }

    @Test
    void findBookByNamedQuery() {
    }

    @Test
    void findBookByTitleWithJPQL() {
    }

    @Test
    void readByTitle() {
    }

    @Test
    void getByTitle() {
    }

    @Test
    void findAllByTitleNotNull() {
    }

    @Test
    void queryByTitle() {
    }
}