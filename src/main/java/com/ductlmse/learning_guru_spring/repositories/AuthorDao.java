package com.ductlmse.learning_guru_spring.repositories;

import com.ductlmse.learning_guru_spring.domain.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    Optional<Author> findById(Long id);
    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);

    Author updateAuthor(Author author);

    Author createNewAuthor(Author author);

    void deleteAuthor(Long id);

    List<Author> findAll(Pageable pageable);

}
