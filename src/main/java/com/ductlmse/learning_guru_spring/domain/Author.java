package com.ductlmse.learning_guru_spring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "author.books", query = "SELECT a FROM Author a JOIN FETCH a.books WHERE a.id = :id"),
        @NamedQuery(name = "author.avatar", query = "SELECT a FROM Author a JOIN FETCH a.avatars WHERE a.id = :id"),
        @NamedQuery(name = "author.certificate", query = "SELECT a FROM Author a JOIN FETCH a.certificate WHERE a.id =:id")
})
@NamedEntityGraphs({
        @NamedEntityGraph(
                name="author.graph.book",
                attributeNodes = @NamedAttributeNode(value = "books", subgraph = "book.publisher"),
                subgraphs = @NamedSubgraph(name = "book.publisher", attributeNodes = @NamedAttributeNode("publishers"))
        )
})
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //    @Where(clause = "type = 'AVATAR'")
    @ToString.Exclude
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Image> avatars = new ArrayList<>();

    //    @Where(clause = "type = 'CERTIFICATE'")
    @ToString.Exclude
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Image> certificate = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    public Author(Long id) {
        this.id = id;
    }

    public Author(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
