package com.ductlmse.learning_guru_spring.repositories;

import com.ductlmse.learning_guru_spring.dto.AuthorPageImpl;

public interface AuthorPageService {
    AuthorPageImpl findByLikeLastName(String lastName);
}
