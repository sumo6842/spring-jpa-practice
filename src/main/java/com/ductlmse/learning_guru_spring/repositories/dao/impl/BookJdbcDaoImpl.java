package com.ductlmse.learning_guru_spring.repositories.dao.impl;

import com.ductlmse.learning_guru_spring.domain.Book;
import com.ductlmse.learning_guru_spring.domain.RowBookMapper;
import com.ductlmse.learning_guru_spring.repositories.BookJdbcDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookJdbcDaoImpl implements BookJdbcDao {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public Optional<Book> findAllBook(Long id) {
        String sql = "SELECT title, id FROM book WHERE id = ?";
        var query = jdbcTemplate.queryForObject(sql, getBookRowMapper(), id);

        return Optional.ofNullable(query);
    }

    private RowBookMapper getBookRowMapper() {
        return new RowBookMapper();
    }
}
