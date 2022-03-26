package com.ductlmse.learning_guru_spring.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowBookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        var id = rs.getLong("id");
        var title = rs.getString("title");

        return new Book(id, title);
    }
}
