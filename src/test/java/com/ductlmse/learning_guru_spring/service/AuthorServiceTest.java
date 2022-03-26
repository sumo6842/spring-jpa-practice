package com.ductlmse.learning_guru_spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ComponentScan(basePackages = {
        "com.ductlmse.learning_guru_spring.service",
        "com.ductlmse.learning_guru_spring.repositories.dao"})
class AuthorServiceTest {

    @Autowired
    AuthorService service;

    @Test
    void getBookFromAuthor() {
        var author = service.getBookFromAuthor(1L);
        author.getBooks().forEach(System.out::println);
    }
}