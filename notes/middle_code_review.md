# Drawbacks according middle code-review

The marked items are fixed.
 ### Non-code
 ____
 - [ ] Create README file according Oleg's recommendations (e-mail)
 - [ ] Set up .gitignore (The .idea folder mustn't reside in the repository)
 - [ ] Separate the main Aveng project and the Spittr educational projects
 - [ ] Fix IDE (Integrated development environment) warnings
 List:
 - Class is a raw type. References to generic type Class<T> should be parameterized              SpittleController.java                /spittr/src/main/java/edu/sam/spittr/controller line 70   Java Problem
 - The method setId(long) from the type SpittleDTO is never used locally     SpittleDTO.java                /spittr/src/main/java/edu/sam/spittr/dto            line 39   Java Problem
 - The type WebMvcConfigurerAdapter is deprecated         WebConfig.java /spittr/src/main/java/edu/sam/aveng/config                line 23   Java Problem
 - The type WebMvcConfigurerAdapter is deprecated         WebConfig.java /spittr/src/main/java/edu/sam/aveng/config                line 36   Java Problem
 - The value of the field CardMapping.frequency is not used             CardMapping.java                /spittr/src/main/java/edu/sam/aveng/domain   line 11   Java Problem
 - The value of the local variable test is not used     InitialController.java                /spittr/src/main/java/edu/sam/aveng/controller              line 28   Java Problem
 - [ ] Apply check-style for the main project
 - [ ] Sort out comments
    - [ ] Delete those that are non-required
    - [ ] Reform the rest in javadoc
 ____
 ### Configuration
 ___
 - [ ] Use the common version for all Spring components
 - [ ] Leave a single database connection configuration (WebConfig + properties file)
 - [ ] Move configuration values in properties file
    - [ ] db connection configuration
 ```java
    // WRONG!
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
 ```
 - [ ] Create file with test properties (path: src/test/resources)
     - [ ] Set up H2 in-memory db connection for integration tests (jdbc:h2:mem)
 ___
 ### View
 ___
 - [ ] Configure page encoding (Test it on Russian language)
 
| ID      | Содержимое    | Тип   | Определение      |
| --------|:-------------:|:-----:| ----------------:|
| 2       | Ð¢ÐµÑÑ,      | NOUN  | Ñ‚ÐµÑÑ‚Ð¾Ð²Ð¾Ðµ |

 - [ ] Set up a tool to check POST request and hidden problems on user side
    - [ ] Fix hidden 404 favicon.ico exception
 - [ ] Settle global page structure
    - [ ] Add header with menu items
    - [ ] Add footer
 ___
 ### Other
 ___
 - [ ] Localize enums

 ```java
    //  forbidden import
    import javax.persistence.*;
 ```
 - [ ] Fix the authorisation drawbacks
    - [ ] Replace default login page with custom
    - [ ] Let know users whether their registration has been successful
- [ ] Fix exceptions of db schema creation occurred on app startup

 Example
 
 Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Constraint "FKHI46VU7680Y1HWVMNNUH4CYBX" already exists; SQL statement:
 alter table user_authority add constraint FKhi46vu7680y1hwvmnnuh4cybx foreign key (user_id) references users [90045-199]

- [ ] Code in the master and develop branches have to be stable
 ___
