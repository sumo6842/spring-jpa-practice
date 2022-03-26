package com.ductlmse.learning_guru_spring.repositories.dao.joinfetch;

import com.ductlmse.learning_guru_spring.domain.*;
import com.ductlmse.learning_guru_spring.repositories.AuthorDynamicCriteriaDao;
import com.ductlmse.learning_guru_spring.utils.Constant.TupleAlias;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorDynamicCriteriaDaoImpl implements AuthorDynamicCriteriaDao {
    private final EntityManager entityManager;

    private CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    private Optional<Author> findAuthorBbyId(Long id) {
        return null;
    }

    @Override
    public Optional<Author> fetchBookToAuthor(Long authorId) {
      return null;
    }

    /**
     * Todo Example to join multiple table:
     * @param authorId
     * @return
     */
    @Override
    public List<Tuple> joinPublisherToAuthor(Long authorId) {
        var builder = getCriteriaBuilder();
        var query = builder.createTupleQuery();
        var root = query.from(Author.class);

        var joinPublisher = root.join(Author_.BOOKS).join(Book_.PUBLISHERS);

        ParameterExpression<Long> _authorId = builder.parameter(Long.class);
        Predicate predicate = builder.conjunction();
        predicate = builder.and(predicate, builder.equal(root.get(Author_.ID), _authorId));
        query.where(predicate)
                .multiselect(
                        joinPublisher.get(Publisher_.ID).alias("publisherID"),
                        joinPublisher.get(Publisher_.NAME).alias("publisherName"));

        return entityManager.createQuery(query)
                .setParameter(_authorId, authorId)
                .getResultList();

    }

    @Override
    public List<Tuple> bookJoinAuthor(Long authorId) {
        var criteriaBuilder = getCriteriaBuilder();
        var tupleQuery = criteriaBuilder.createTupleQuery();
        var from = tupleQuery.from(Author.class);

        var joinBook = from.join(Author_.BOOKS);
        ParameterExpression<Long> _authorId = criteriaBuilder.parameter(Long.class);
        Predicate predicate = criteriaBuilder.conjunction();

        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(from.get(Author_.ID), _authorId));

        //From Author a where
        tupleQuery.where(predicate)
                .multiselect(joinBook.get(Book_.ID).alias(TupleAlias.BOOK_ID),
                        joinBook.get(Book_.TITLE).alias(TupleAlias.BOOK_TITLE));

        return entityManager.createQuery(tupleQuery)
                .setParameter(_authorId, authorId)
                .getResultList();
    }

    @Override
    public List<Book> publisherJoinBook(Long bookId) {
        return null;
    }

    @Override
    public List<Author> bookLeftJoinAuthor(String titleBook) {
        return null;
    }

    @Override
    public List<Book> bookGetFromAuthor(Long id) {
      return null;
    }
}
