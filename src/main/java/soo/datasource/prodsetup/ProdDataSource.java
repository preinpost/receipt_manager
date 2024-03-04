package soo.datasource.prodsetup;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@Getter
@Profile("prod")
@ConfigurationProperties(prefix = "receipt")
public class ProdDataSource {

    private final String url = "jdbc:postgresql://localhost:5432/receipt_db";
    private final String user;
    private final String password;
    private final String driverClassName = "org.postgresql.Driver";

    public ProdDataSource(String user, String password) {
        this.user = user;
        this.password = password;
    }
}
