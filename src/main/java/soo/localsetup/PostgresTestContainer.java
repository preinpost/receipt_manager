package soo.localsetup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;

@Profile(value = {"test", "local", "dev"})
@Configuration
public class PostgresTestContainer {

    @Bean
    protected PostgreSQLContainer<?> postgreSQLContainer() {
        PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16");
        postgreSQLContainer.withDatabaseName("receipt_db");
        postgreSQLContainer.withUsername("test");
        postgreSQLContainer.withPassword("test");

        postgreSQLContainer.start();
        return postgreSQLContainer;
    }
}
