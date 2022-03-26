# spring_jpa_practice
### General:
Query, Relationship, Other
#### Jpa Queries
* Query, using jpql syntax 
* NativeQueries, SQL syntax
* Criteria Api Query,programmatically
#### Query
* sub-types: TypedQuery, NamedQuery
    * Pros:   
      - Dynamic query string
      - Portable queries
    * Cons: Compile to SQL statement depending on the query plan cache
  
* Typed query: 
    * Pay attention to the return statement, avoid casting Exceptions.
```
Ex: EntityManager.createQuery("From Author a Where a.id =:id", Author.class);
```
   * Pros: When we create a query using EntityManager, can build dynamic query Strings.
   * Cons: xxx
    
* Named query: Defining on the entity class itself, providing a centralized, quicl and easy way to read and find related queries
```
@NamedQueries{
@NamedQuery(name = "Author.list-book",
    query = "SELECT a.listBook FROM Author a WHERE a.id =:id"),
}
EntityManager.createNamedQuery("Author.list-book");
```
  * Pros: 
        * Named-queries are compile and validated when persistence unit is loaded, so compiled once only.
        * Centralize named-queries to make them easy maintains.
  * Cons: 
              * It is always static
              * Dynamic sorting not supported
	  
         
#### Native queries

```
entityManager.createNativevQuery("SELECT name, id FROM Athor");
```

* Pros:
	* In case query complex, jpa-generated SQL Statement aren't the most optimized using native more effective
	* Native query allow using database vendor-specific features, some time those feature give us queried better performance
* Cons: The portability is maybe restrict.
  
#### Criteria Queries API:
* programmatically-built, type-safe queries
```
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
```
