package com.ductlmse.learning_guru_spring.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface AuthorPageRepository extends PagingAndSortingRepository<Author, Long> {
    @Query("SELECT a FROM Author a WHERE a.lastName =: firstName and a.lastName =: lastName")
    Page<Author> pageAuthorLastNameAndFirstName(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName, Pageable pageable);

    @Query("SELECT a FROM Author a WHERE a.lastName like :lastName")
    Page<Author> pageAuthorByLikeLastName(@Param("lastName") String lastName, Pageable pageable);
}