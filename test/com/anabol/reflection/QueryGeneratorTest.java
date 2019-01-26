package com.anabol.reflection;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryGeneratorTest {
    @Test
    public void testGetAll() {
        QueryGenerator queryGenerator = new QueryGenerator();
        String getAllSql = queryGenerator.getAll(Person.class);
        String expectedSql = "SELECT id, person_name, salary FROM persons;";
        assertEquals(expectedSql, getAllSql);
    }

    @Test
    public void testInsert() {
        QueryGenerator queryGenerator = new QueryGenerator();
        String getAllSql = queryGenerator.insert(new Person(1, "Alex", 1234.56));
        String expectedSql = "INSERT INTO persons(id, person_name, salary) VALUES (1, 'Alex', 1234.56);";
        assertEquals(expectedSql, getAllSql);
    }

    @Test
    public void testUpdate() {
        QueryGenerator queryGenerator = new QueryGenerator();
        String getAllSql = queryGenerator.update(new Person(1, "John", 678.9));
        String expectedSql = "UPDATE persons SET person_name = 'John', salary = 678.9 WHERE id = 1;";
        assertEquals(expectedSql, getAllSql);
    }

    @Test
    public void testGetById() {
        QueryGenerator queryGenerator = new QueryGenerator();
        String getAllSql = queryGenerator.getById(Person.class, 1);
        String expectedSql = "SELECT id, person_name, salary FROM persons WHERE id = 1;";
        assertEquals(expectedSql, getAllSql);
    }

    @Test
    public void testDelete() {
        QueryGenerator queryGenerator = new QueryGenerator();
        String getAllSql = queryGenerator.delete(Person.class, 1);
        String expectedSql = "DELETE FROM persons WHERE id = 1;";
        assertEquals(expectedSql, getAllSql);
    }

}
