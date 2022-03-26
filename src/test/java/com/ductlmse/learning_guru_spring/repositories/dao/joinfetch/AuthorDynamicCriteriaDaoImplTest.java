package com.ductlmse.learning_guru_spring.repositories.dao.joinfetch;

import com.ductlmse.learning_guru_spring.domain.Book;
import com.ductlmse.learning_guru_spring.domain.Publisher;
import com.ductlmse.learning_guru_spring.repositories.AuthorDynamicCriteriaDao;
import com.ductlmse.learning_guru_spring.utils.Constant.TupleAlias;
import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.EntityManager;
import java.util.stream.Collectors;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ComponentScan(basePackages = {"com.ductlmse.learning_guru_spring.repositories"})
class AuthorDynamicCriteriaDaoImplTest {
    @Data
    @ToString
    private class TupleMapping {
        String authorFirstName;
        String authorLastName;
        String bookTitle;
        String publisherName;

        public TupleMapping(String authorFirstName, String authorLastName, String bookTitle, String publisherName) {
            this.authorFirstName = authorFirstName;
            this.authorLastName = authorLastName;
            this.bookTitle = bookTitle;
            this.publisherName = publisherName;
        }
    }

    @Autowired
    EntityManager entityManager;
    AuthorDynamicCriteriaDao authorDao;

    @BeforeEach
    void setUp() {
        authorDao = new AuthorDynamicCriteriaDaoImpl(entityManager);
    }

    @Test
    void fetchBookToAuthor() {
        var author = authorDao.fetchBookToAuthor(1L);
        author.ifPresent(a -> a.getBooks().forEach(System.out::println));
    }

    @Test
    void fetchPublisherToAuthor() {
        var author = authorDao.fetchBookToAuthor(1L);
        author.ifPresent(a -> a.getBooks().forEach(System.out::println));
    }

    @Test
    void joinPublisherToAuthor() {
        var tuples = authorDao.joinPublisherToAuthor(1L);
        var listTupleObject = tuples.stream()
                .map(tuple -> {
                    var publisherName = tuple.get("publisherName", String.class);
                    var publisherID = tuple.get("publisherID", Long.class);
                    return new Publisher(publisherID, publisherName);
                }).collect(Collectors.toList());
        System.out.println("This is tuple");
        listTupleObject.forEach(System.out::println);
    }

    @Test
    void bookJoinAuthor() throws Exception {
        var author = authorDao.bookJoinAuthor(1L);
        var collect = author.stream()
                .map(tuple -> {
                    String bookName = tuple.get(TupleAlias.BOOK_TITLE, String.class);
                    Long bookId = tuple.get(TupleAlias.BOOK_ID, Long.class);

                    return new Book(bookId, bookName);
                }).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Test
    void bookLeftJoinAuthor() {
        var books = authorDao.bookGetFromAuthor(1L);
        books.forEach(System.out::println);
    }
}