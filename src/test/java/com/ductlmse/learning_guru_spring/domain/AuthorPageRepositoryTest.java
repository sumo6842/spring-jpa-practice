package com.ductlmse.learning_guru_spring.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ComponentScan(basePackages = {"com.ductlmse.learning_guru_spring.repositories"})
class AuthorPageRepositoryTest {
    @Autowired
    AuthorPageRepository repositories;

    @BeforeEach
    void setUp() {
    }

    @Test
    void pageAuthorLastNameAndFirstName() {
        var authors = repositories.pageAuthorByLikeLastName("%Wall%", PageRequest.of(0, 25));
        authors.forEach(System.out::println);
    }

    @Test
    void pageAuthorByLikeLastName() {
    }
}