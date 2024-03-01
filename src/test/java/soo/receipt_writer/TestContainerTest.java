package soo.receipt_writer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
public class TestContainerTest {

    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:16");

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
    }

    @Test
    void someTest() {
        System.out.println("postgreSQLContainer.getJdbcUrl() = " + postgreSQLContainer.getJdbcUrl());
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }
}
