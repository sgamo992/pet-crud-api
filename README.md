# Migrating from H2 to MongoDB in Spring Boot

## Steps

1. Add MongoDB Dependency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>

```

2. Change Data Source Configuration  
   - Update `application.properties`:

```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=dbname
spring.data.mongodb.username=username
spring.data.mongodb.password=psw
```

Remove properties related to H2:

```properties
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

```



2. Update Entity Classes
   - Replace `@Entity` with `@Document` (`org.springframework.data.mongodb.core.mapping.Document`)
   - Remove JPA annotations like `@Table`, `@Column`
   - Use `@Id` for the primary key

**Example:**
```java
// Before
@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}

// After
@Document
public class Pet {
    @Id
    private String id;
    private String name;
}
```

3. Change Repository Interfaces 
   - replace `JpaRepository` with `MongoRepository`

**Example:**
```java
// Before
public interface PetRepository extends JpaRepository<Pet, Long> {}

// After
public interface PetRepository extends MongoRepository<Pet, String> {}
```

