# spring_jpa_practice
### General
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
	* Pros: 
		* Named-queries are compile and validated when persistence unit is loaded, so compiled once only.
		* Centrialize named-queries to make them easy maintains.
	* Cons: 
		* It is always static
		* Dynamic sorting not supported
	  
		```
			@NamedQueries{
				@NamedQuery(name = "Author.list-book", 
					query = "SELECT a.listBook FROM Author a WHERE a.id =:id"),
			}
			
			EntityManager.createNamedQuery("Author.list-book");
		```
#### Native queries:
		
		EntityManager.createNativeQuery(SELECT name, id FROM Author Where id = ?1");
		
* Pros:
	* In case query complex, jpa-generated SQL Statement aren't the most optimized using native more effective
	* Natived query allow to use database vendor-specific features, some time those feature give us querried better performance
* Cons: The portability is maybe restrict.
  
	
