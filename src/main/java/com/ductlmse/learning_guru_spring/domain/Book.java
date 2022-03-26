package com.ductlmse.learning_guru_spring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Book.CustomerDTO",
                query = "SELECT id, title " +
                        "FROM book " +
                        "WHERE id = ?",
                resultSetMapping = "BOOK_PUBLISHER_NAME"
        )
})
@SqlResultSetMapping(name = "BOOK_PUBLISHER_NAME",
        entities = {
                @EntityResult(
                        entityClass = Book.class,
                        fields = {
                                @FieldResult(name = "id", column = "id"),
                                @FieldResult(name = "title", column = "title"),
                        }),
})
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String isbn;
    private String title;

    public Book(String title) {
        this.title = title;
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book insertBook(Author author) {
        author.getBooks().add(this);
        this.setAuthor(author);
        return this;
    }

    public Book(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    @ManyToMany
    @JoinTable(name = "publisher_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Set<Publisher> publishers = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                '}';
    }



}
