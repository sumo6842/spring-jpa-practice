package com.ductlmse.learning_guru_spring.dto;

import com.ductlmse.learning_guru_spring.domain.Author;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class AuthorPageImpl extends PageImpl<AuthorDto> implements Serializable {

    static final long serialVersionUID = 1114715135625836949L;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AuthorPageImpl(
            @JsonProperty("content") List<AuthorDto> content,
            @JsonProperty("page_number") int number,
            @JsonProperty("page_size") int size,
            @JsonProperty("totalElements") Long totalElements,
            @JsonProperty("pageable") JsonNode pageable,
            @JsonProperty("totalPages") int totalPages,
//            @JsonProperty("sort") JsonNode sort,
            @JsonProperty("first") boolean first,
            @JsonProperty("numberOfElements") int numberOfElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public AuthorPageImpl(List<AuthorDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public AuthorPageImpl(List<AuthorDto> content) {
        super(content);
    }
}
