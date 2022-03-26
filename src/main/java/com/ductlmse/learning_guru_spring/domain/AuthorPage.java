package com.ductlmse.learning_guru_spring.domain;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AuthorPage extends PageImpl<Author> {
    public AuthorPage(List<Author> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public AuthorPage(List<Author> content) {
        super(content);
    }
}
