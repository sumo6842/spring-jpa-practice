package com.ductlmse.learning_guru_spring.repositories.dao.impl;

import com.ductlmse.learning_guru_spring.repositories.AuthorDao;
import com.ductlmse.learning_guru_spring.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorAroundDaoImpl implements AuthorDao {

    private final EntityManager entityManager;

    @Override
    public Optional<Author> findById(Long id) {
        String sql = "SELECT a FROM Author a Where a.id =:id";
        var query = entityManager.createQuery(sql, Author.class);
        var author = query.setParameter("id", id).getSingleResult();
        return Optional.ofNullable(author);
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
