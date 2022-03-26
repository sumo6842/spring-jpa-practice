package com.ductlmse.learning_guru_spring.repositories.dao.impl;

import com.ductlmse.learning_guru_spring.repositories.AuthorDao;
import com.ductlmse.learning_guru_spring.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorJpqlDao implements AuthorDao {
    private final EntityManagerFactory emf;

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Optional<Author> findById(Long id) {
        String sql = "SELECT a FROM Author a WHERE a.id =:id";
        var entityManager = getEntityManager();
        var _author = entityManager.createQuery(sql, Author.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.ofNullable(_author);
    }

    @Override
    public Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName) {
        String sql = "SELECT a FROM Author a WHERE a.firstName = :firstName and a.lastName =:lastName";
        var resultList = getEntityManager()
                .createQuery(sql, Author.class)
                .getResultList();
        return Optional.empty();
    }

    @Transactional
    @Override
    public Author updateAuthor(Author author) {
        String sql = "UPDATE Author a " +
                "SET a.firstName =:firstName, a.lastName =:lastName " +
                "WHERE a.id =:id";

        var _author = getEntityManager().createQuery(sql, Author.class)
                .setParameter("firstName", author.getFirstName())
                .setParameter("lastName", author.getLastName())
                .setParameter("id", author.getId())
                .getSingleResult();
        return _author;
    }

    //Need To test this
    @Transactional
    @Override
    public Author createNewAuthor(Author author) {
        var entityManager = getEntityManager();
        entityManager.persist(author);
        return author;
    }

    @Override
    public void deleteAuthor(Long id) {

    }

    @Override
    public List<Author> findAll(Pageable pageable) {
        return null;
    }
}
