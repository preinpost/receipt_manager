package soo.datasource.prodsetup;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "spring.datasource")
public class ProdDataSource {

    private final String url = "jdbc:postgresql://localhost:5432/receipt_db";
    private final String RECEIPT_USER;
    private final String RECEIPT_PASSWORD;
    private final String driverClassName = "org.postgresql.Driver";

    public ProdDataSource(String RECEIPT_USER, String RECEIPT_PASSWORD) {
        this.RECEIPT_USER = RECEIPT_USER;
        this.RECEIPT_PASSWORD = RECEIPT_PASSWORD;
    }
}
