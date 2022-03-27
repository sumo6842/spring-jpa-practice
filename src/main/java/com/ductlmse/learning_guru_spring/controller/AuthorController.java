package com.ductlmse.learning_guru_spring.controller;

import com.ductlmse.learning_guru_spring.repositories.AuthorPageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorPageService service;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @GetMapping("/author")
    public ResponseEntity<?> findAllAuthor(@RequestParam("last-name") String lastName) {

        var result = service.findByLikeLastName(lastName);
        LOGGER.info("_this is result {}", result);
        return ok(result);
    }

}
