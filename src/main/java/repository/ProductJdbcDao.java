package repository;

import congifuration.DatabaseConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class ProductJdbcDao {

    private JdbcTemplate jdbcTemplate;

    public ProductJdbcDao(){
        DataSource postgres = DatabaseConfiguration.getInstance().postgres();

        jdbcTemplate = new JdbcTemplate(postgres);
    }

}
