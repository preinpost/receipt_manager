package soo.localsetup;

import com.github.dockerjava.api.model.PortBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Objects;
import java.util.Optional;

@Profile(value = {"test", "local", "dev"})
@Configuration
public class PostgresTestContainer {

    @Bean
    protected PostgreSQLContainer<?> postgreSQLContainer() {
        PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16");
        postgreSQLContainer.withCreateContainerCmdModifier(cmd -> {
            cmd.withName("receipt_db");
            cmd.withHostName("receipt_db");

            // 특정 port binding
            Optional.ofNullable(cmd.getHostConfig()).ifPresent(hostConfig -> {
                hostConfig.withPortBindings(PortBinding.parse("40111:5432"));
            });

            cmd.withAliases("receipt_db");
        });
        postgreSQLContainer.withDatabaseName("receipt_db");
        postgreSQLContainer.withUsername("test");
        postgreSQLContainer.withPassword("test");

        postgreSQLContainer.start();
        return postgreSQLContainer;
    }
}
