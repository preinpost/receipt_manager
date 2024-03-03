package soo.datasource.prodsetup;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@RequiredArgsConstructor
@EnableConfigurationProperties(ProdDataSource.class)
public class ProdDataSourceConfig {

    private final ProdDataSource prodDataSource;

    @Bean
    public DataSource prodDataSource() {
        return DataSourceBuilder.create()
                .url(prodDataSource.getUrl())
                .driverClassName(prodDataSource.getDriverClassName())
                .username(prodDataSource.getRECEIPT_USER())
                .password(prodDataSource.getRECEIPT_PASSWORD())
                .build();
    }
}
