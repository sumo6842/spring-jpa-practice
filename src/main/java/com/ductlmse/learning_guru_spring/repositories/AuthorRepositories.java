package com.ductlmse.learning_guru_spring.repositories;


import com.ductlmse.learning_guru_spring.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositories extends JpaRepository<Author, Long> {


    @Query("SELECT a FROM Author a WHERE a.lastName =:lastname")
    List<Author> findAllAuthorByLastName(@Param("lastname") String lastName);

    @Query("SELECT a FROM Author a WHERE a.lastName =:lastname and a.firstName =:firstname")
    Optional<Author> findAuthorByFirstNameAndLastName(@Param("lastname") String firstName, @Param("lastname") String lastName);

    @Query("SELECT a FROM Author a WHERE a.lastName = ?1 and a.firstName = ?2")
    Optional<Author> findAuthorByFirstNameAndLastNamePosition(String firstName, String lastName);

    @Query("SELECT a FROM Author a WHERE a.lastName =:lastname")
    Page<Author> findAuthorByLastName(@Param("lastname") String lastName, Pageable pageable);

    @Query(value = "SELECT * FROM author WHERE first_name =:firstname and last_name =:lastname", nativeQuery = true)
    Optional<Author> findAuthorByName(@Param("firstname") String firstName,@Param("lastname") String lastName);

    @Query("SELECT a FROM Author a WHERE a.id =:id")
    Optional<Author> findAuthorAndAvatar(Long id);

}
