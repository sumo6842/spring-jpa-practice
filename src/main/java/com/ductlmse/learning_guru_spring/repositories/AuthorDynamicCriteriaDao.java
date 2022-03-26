package com.ductlmse.learning_guru_spring.repositories;

import com.ductlmse.learning_guru_spring.domain.Author;
import com.ductlmse.learning_guru_spring.domain.Book;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public interface AuthorDynamicCriteriaDao {
    Optional<Author> fetchBookToAuthor(Long authorId);

    //Select all publisher has books owns author by id author
    List<Tuple> joinPublisherToAuthor(Long authorId);

    //
    List<Tuple> bookJoinAuthor(Long authorId);

    //
    List<Book> publisherJoinBook(Long bookId);

    //left join book author
    List<Author> bookLeftJoinAuthor(String titleBook);

    List<Book> bookGetFromAuthor(Long id);

}
