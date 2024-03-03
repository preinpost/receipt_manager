package soo.datasource.localsetup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Profile(value = {"test", "local", "dev"})
@Slf4j
@Configuration
@RequiredArgsConstructor
public class LocalDataSource {

    private final PostgreSQLContainer<?> postgreSQLContainer;

    @Bean
    protected DataSource dataSource() {

        String url = "jdbc:postgresql://localhost:" +
                postgreSQLContainer.getMappedPort(5432) +
                "/"
                + postgreSQLContainer.getDatabaseName();

        log.debug("url = {}", url);

        return DataSourceBuilder.create()
                .url(url)
                .driverClassName("org.postgresql.Driver")
                .username(postgreSQLContainer.getUsername())
                .password(postgreSQLContainer.getPassword())
                .build();
    }
}
