package com.ductlmse.learning_guru_spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@RequiredArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @ManyToMany(
            mappedBy = "publishers",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private Set<Book> books = new LinkedHashSet<>();

    private LocalDateTime since;


    public Publisher(String name) {
        this.name = name;
    }

    public Publisher(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //utils method:
    public Publisher addBook(Book book) {
        this.books.add(book);
        book.getPublishers().add(this);
        return this;
    }

    public Publisher removeBook(Book book) {
        this.books.remove(book);
        book.getPublishers().remove(this);
        return this;
    }

}
