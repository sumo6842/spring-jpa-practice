package com.ductlmse.learning_guru_spring.repositories.dao;

import com.ductlmse.learning_guru_spring.domain.Author;
import com.ductlmse.learning_guru_spring.domain.AuthorPageRepository;
import com.ductlmse.learning_guru_spring.dto.AuthorDto;
import com.ductlmse.learning_guru_spring.dto.AuthorPageImpl;
import com.ductlmse.learning_guru_spring.repositories.AuthorPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorPageServiceImpl implements AuthorPageService {
    private final AuthorPageRepository authorPageRepository;

    public AuthorPageImpl findByLikeLastName(String lastName) {
        var listAuthor = authorPageRepository
                .pageAuthorByLikeLastName("%" + lastName + "%", PageRequest.of(0, 25));
        System.out.println("This is result: ");
        listAuthor.getContent().forEach(System.out::println);

        System.out.println("This is page: " + PageRequest.of(
                listAuthor.getPageable().getPageNumber(),
                listAuthor.getPageable().getPageSize()));
        System.out.println("This is total: " + listAuthor.getTotalElements());
        return new AuthorPageImpl(
                listAuthor.getContent()
                        .stream()
                        .map(this::mapperAuthorDto)
                        .collect(Collectors.toList()),
                PageRequest.of(
                        listAuthor.getPageable().getPageNumber(),
                        listAuthor.getPageable().getPageSize()),
                listAuthor.getTotalElements());
    }

    private AuthorDto mapperAuthorDto(Author author) {
        return new AuthorDto(author.getId(),
                author.getFirstName(), author.getLastName());
    }

}
