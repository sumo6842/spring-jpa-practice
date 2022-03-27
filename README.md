# spring-jpa-practice
#### Table Of Content
* [Jpa Queries](#jpa-queries)
* [Query](#query)
* [Named Graph](#named-graphs)
* [Native queries](#native-queries)
* [Criteria API](#criteria-queries-api)
* [DAO pattern](#dao-pattern)
* [Spring Jdbc Templates](#spring-jdbc-templates)
* [Paging and Sort](#paging-and-sort)
* [Relationship and Other](#relationship-and-other)

### Meta Model (supports criteria api)
### Jpa Queries
* Query, using jpql syntax 
* NativeQueries, SQL syntax
* Criteria Api Query,programmatically
#### Query
* _sub-types_: TypedQuery, NamedQuery
    * Pros:   
      - Dynamic query string
      - Portable queries
    * Cons: Compile to SQL statement depending on the query plan cache
  
* _Typed query_: 
    * Pay attention to the return statement, avoid casting Exceptions.
    * Pros: When we create a query using EntityManager, can build dynamic query Strings.
    * Cons: xxx
```
Ex: EntityManager.createQuery("From Author a Where a.id =:id", Author.class);
```
* _Named query_: Defining on the entity class itself, providing a centralized, quicl and easy way to read and find related queries
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
#### Named Graphs:
    
#### Native queries

```
entityManager.createNativeQuery("SELECT name, id FROM Athor");
```
* Pros:
	* In case query complex, jpa-generated SQL Statement aren't the most optimized using native more effective
	* Native query allow using database vendor-specific features, some time those feature give us queried better performance
* Cons: The portability is maybe restrict.
  

#### Criteria Queries API:
* programmatically-built, type-safe queries.
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


#### DAO pattern
#### Spring Jdbc Templates
* _RowMapper_: 
    ```
  public class RowAuthorMapper implements RowMapper<Book> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        var id = rs.getLong("id");
        var firstName = rs.getString("first_name");
        var lastName = rs.getString("last_name");
        return new Author(id, firstName, lastName);
      }
  }
    ```
* _JdbcTemplate_:
    ```
  private final JdbcTemplate jdbcTemplate;
  
  private AuthorJdbcMapper authorMapper() {
        return new AuthorJdbcMapper();
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Author> findById(Long id) {
      String sql = "SELECT * FROM author WHERE id = ?";
      var author = jdbcTemplate.queryForObject(sql, authorMapper(), id);
      return Optional.ofNullable(author);
  }
    
  @Transactional
  @Override
  public Author createNewAuthor(Author author) {
      String sql = "INSERT INTO author(first_name, last_name) VALUES (?,?)";
      var update = jdbcTemplate.update(sql,author.getFirstName(), author.getLastName());
      System.out.println(update);
      return null;
  } 
  ```
* _NamedParameterJdbcTemplate_:
  ```
   private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
   @Transactional
   @Override
   public void deleteAuthor(Long id) {
       String sql = "DELETE FROM author WHERE id = :authorId";
       namedParameterJdbcTemplate.update(sql,
               new MapSqlParameterSource().addValue("authorId", id));
   }
  ```
### Paging and Sort:
* Native Queries: 
* PageImpl, Page, Sort 
  * PageImpl
    ```
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
            @JsonProperty("sort") JsonNode sort,
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
    ```
### Relationship and Other

### Meta Model (supports criteria api)
