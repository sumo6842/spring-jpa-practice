package com.ductlmse.learning_guru_spring.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorDto implements Serializable {
    private final Long id;
    private final String firstName;
    private final String lastName;
}
