# Hibernate ORM Bug Reproduction Project

## Overview

This project demonstrates a reproducible bug encountered while using Hibernate ORM using [**`JpaCriteriaQuery.orderBy()`**](https://docs.jboss.org/hibernate/stable/orm/javadocs/org/hibernate/query/criteria/JpaCriteriaQuery.html#orderBy(jakarta.persistence.criteria.Order...)) and nested [**`CriteriaBuilder.construct()`**](https://docs.jboss.org/hibernate/stable/orm/javadocs/org/hibernate/query/criteria/HibernateCriteriaBuilder.html#construct(java.lang.Class,jakarta.persistence.criteria.Selection...)). 
It provides a minimal, self-contained example with clear steps to reproduce the issue. 

**Hibernate version:** *6.6.2.Final*  
**Database and version:** *H2 2.3.232*  
**Java version:** *21*  
**Build tool and version:** *Gradle 8.11.1*

Please follow the instructions below to replicate the bug and observe the behavior.

## Steps to Reproduce

1. **Build the project:**
    ```bash
    gradlew build
    ```

2. **Run the tests:**
    ```bash
    gradlew test
    ```
    
   The tests are designed to isolate and demonstrate the bug.
   They demonstrate the difference between using a nested cb.construct approach for creating a DTO and using a singular cb.construct.
   Every nested cb.construct used is causing every field selected thereafter to be offset by an extra +1 in the orderBy clause.

## Project Structure

- **`src/main/java`**:  
  Minimal entity model and classes required to replicate the bug.
  
- **`src/test/java`**:  
  Single test class with 6 tests specifically designed to demonstrate the issue.

## Expected Behavior when using nested CriteriaBuilder.construct()

- *The query should return a list of DTO/entities.*
- *The result should be sorted according to the field passed to the orderBy clause.*

## Actual Behavior when using nested CriteriaBuilder.construct()

- *The query should return a list of DTO/entities.*
- *The result is sorted on a different field*
- *The sorted field depends on the amount of CriteriaBuilder.construct() expressions in front of it in the select clause*

## Additional Notes

- **Debugging:**  
  Debugging the issues point to an issue located in the [**`BaseSqmToSqlAstConverter`**](https://docs.jboss.org/hibernate/stable/orm/javadocs/org/hibernate/query/sqm/sql/BaseSqmToSqlAstConverter.html).<br>
  Specifically the method:<br>
  [**`BaseSqmToSqlAstConverter.resolveGroupOrOrderByExpression()`**](https://docs.jboss.org/hibernate/stable/orm/javadocs/org/hibernate/query/sqm/sql/BaseSqmToSqlAstConverter.html#resolveGroupOrOrderByExpression(org.hibernate.query.sqm.tree.expression.SqmExpression))<br>
   and associated private method:<br>
 **`private int indexOfExpression(int offset, List<? extends SqmAliasedNode<?>> selections, SqmExpression<?> node)`**<br>
 seem to be the culprit.<br>
 The calculated indexOfExpression creases by one too many for each encountered nested [**`SqmDynamicInstantiation`**](https://docs.jboss.org/hibernate/stable/orm/javadocs/org/hibernate/query/sqm/tree/select/SqmDynamicInstantiation.html).<br>
 Thus leading to the aformentioned bug with sorting by incorrectly referencing the wrong positional select argument.

---

*By providing this detailed reproduction setup, we hope to facilitate a quick and effective resolution to the reported issue.*
