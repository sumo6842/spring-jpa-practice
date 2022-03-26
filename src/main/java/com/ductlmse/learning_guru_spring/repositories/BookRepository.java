package com.ductlmse.learning_guru_spring.repositories;

import com.ductlmse.learning_guru_spring.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByTitle(String title);

    @Query(value = "SELECT * FROM  book WHERE title =:title", nativeQuery = true)
    Book findBookByTitleNativeQuery(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.title =:title")
    Book findBookByNamedQuery(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    Book findBookByTitleWithJPQL(String title);

    Book readByTitle(String title);

    Optional<Book> getByTitle(String title);

    Stream<Book> findAllByTitleNotNull();

    @Async
    Future<Book> queryByTitle(String title);

}