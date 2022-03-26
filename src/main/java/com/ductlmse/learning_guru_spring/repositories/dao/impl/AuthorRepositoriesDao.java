package com.ductlmse.learning_guru_spring.repositories.dao.impl;

import com.ductlmse.learning_guru_spring.repositories.AuthorDao;
import com.ductlmse.learning_guru_spring.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorRepositoriesDao implements AuthorDao {
    @Override
    public Optional<Author> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName) {
        return Optional.empty();
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public Author createNewAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthor(Long id) {

    }

    @Override
    public List<Author> findAll(Pageable pageable) {
        return null;
    }
}
