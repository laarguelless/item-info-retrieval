package org.laarguelless.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class DataSource {
    private static HikariConfig config;
    private static HikariDataSource ds;
    private static Jdbi jdbi;

    static {
        config = new HikariConfig( );
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://postgres-docker:5432/db");
        config.setUsername("docker");
        config.setPassword("docker");
        ds = new HikariDataSource( config );
        jdbi = Jdbi.create(ds);
        jdbi.installPlugin(new SqlObjectPlugin());

    }

    private DataSource() {}

    public static Jdbi jdbi(){
        return jdbi;
    }
}
