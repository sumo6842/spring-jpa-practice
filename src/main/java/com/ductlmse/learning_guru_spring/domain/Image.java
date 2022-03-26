package com.ductlmse.learning_guru_spring.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String type;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Image image = (Image) o;
        return id != null && Objects.equals(id, image.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
