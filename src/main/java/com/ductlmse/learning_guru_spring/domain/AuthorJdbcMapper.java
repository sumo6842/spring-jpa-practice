package com.ductlmse.learning_guru_spring.domain;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AuthorJdbcMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        var id = rs.getLong("id");
        var first_name = rs.getString("first_name");
        var last_name = rs.getString("last_name");
        var author = new Author();
        author
                .setFirstName(first_name)
                .setLastName(last_name)
                .setId(id);
        return author;

    }
}
