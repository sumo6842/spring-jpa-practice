package com.ductlmse.learning_guru_spring.repositories.dao.impl;

import com.ductlmse.learning_guru_spring.repositories.AuthorDao;
import com.ductlmse.learning_guru_spring.domain.Author;
import com.ductlmse.learning_guru_spring.domain.AuthorJdbcMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorJdbcTemplateDao implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Transactional
    @Override
    public void deleteAuthor(Long id) {
        String sql = "DELETE FROM author WHERE id = :authorId";
        namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource().addValue("authorId", id));
    }

    private AuthorJdbcMapper authorMapper() {
        return new AuthorJdbcMapper();
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Author> findById(Long id) {
        String sql = "SELECT * FROM author WHERE id = ?";
        var author = jdbcTemplate.queryForObject(sql, authorMapper(), id);
        return Optional.ofNullable(author);
    }

    @Transactional
    @Override
    public Author createNewAuthor(Author author) {
        String sql = "INSERT INTO author(first_name, last_name) VALUES (?,?)";
        var update = jdbcTemplate.update(sql,author.getFirstName(), author.getLastName());
        System.out.println(update);
        return null;
    }
    @Override
    public Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName) {
        String sql = "SELECT * FROM author WHERE first_name = ? and last_name = ?";

        var author = jdbcTemplate.queryForObject(sql, authorMapper(), firstName, lastName);

        return Optional.ofNullable(author);
    }

    @Transactional
    @Override
    public Author updateAuthor(Author author) {
        String query = "UPDATE author SET first_name = ?, last_name = ? WHERE id = ?";

        var _author = jdbcTemplate.update(query,
                author.getFirstName(), author.getLastName(), author.getId());
        System.out.println(_author);
        return null;
    }


    @Override
    public List<Author> findAll(Pageable pageable) {
        return null;
    }
}
