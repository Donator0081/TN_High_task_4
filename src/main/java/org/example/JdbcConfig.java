package org.example;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class JdbcConfig {
    public static DataSource createDataSource() {
        final String url =
                "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(url);
        return dataSource;
    }
}
