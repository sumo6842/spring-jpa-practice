package com.ductlmse.learning_guru_spring.repositories.dao;

import com.ductlmse.learning_guru_spring.domain.Author;
import com.ductlmse.learning_guru_spring.domain.AuthorPageRepository;
import com.ductlmse.learning_guru_spring.dto.AuthorDto;
import com.ductlmse.learning_guru_spring.dto.AuthorPageImpl;
import com.ductlmse.learning_guru_spring.repositories.AuthorPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorPageServiceImpl implements AuthorPageService {
    private final AuthorPageRepository authorPageRepository;

    @Cacheable(cacheNames = "author_last_name", key = "lastName")
    public AuthorPageImpl findByLikeLastName(String lastName) {
        var listAuthor = authorPageRepository
                .pageAuthorByLikeLastName(
                        "%" + lastName + "%",
                        PageRequest.of(0, 25));
        return new AuthorPageImpl(
                listAuthor.getContent()
                        .stream()
                        .map(this::mapperAuthorDto)
                        .collect(Collectors.toList()),
                PageRequest.of(
                        listAuthor.getPageable().getPageNumber(),
                        listAuthor.getPageable().getPageSize(),
                        Sort.by("firstName").descending()),
                listAuthor.getTotalElements());
    }

    private AuthorDto mapperAuthorDto(Author author) {
        return new AuthorDto(author.getId(),
                author.getFirstName(), author.getLastName());
    }

}
