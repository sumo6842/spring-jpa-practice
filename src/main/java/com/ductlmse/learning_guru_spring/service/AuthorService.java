package com.ductlmse.learning_guru_spring.service;

import com.ductlmse.learning_guru_spring.domain.Author;
import com.ductlmse.learning_guru_spring.repositories.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorService {
    private final AuthorDao authorDao;

    @Autowired
    public AuthorService(@Qualifier("authorAroundDaoImpl") AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Transactional(readOnly = true)
    public Author getBookFromAuthor(Long authorId) {
        return authorDao.findById(1L).get();
    }
}
