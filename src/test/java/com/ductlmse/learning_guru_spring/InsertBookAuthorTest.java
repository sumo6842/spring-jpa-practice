package com.ductlmse.learning_guru_spring;


import com.ductlmse.learning_guru_spring.domain.Author;
import com.ductlmse.learning_guru_spring.domain.Book;
import com.ductlmse.learning_guru_spring.repositories.AuthorRepositories;
import com.ductlmse.learning_guru_spring.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "com.ductlmse.learning_guru_spring")
public class InsertBookAuthorTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepositories authorRepository;


    @Test
    void insertBookAuthor() throws Exception {
        Book newBook = new Book(1L, "New To Java");
        Author author = authorRepository.findById(1L).get();

        Book book = newBook.insertBook(author);

        bookRepository.save(newBook);
        authorRepository.save(author);

    }
}
