package org.laarguelless.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class JDBI {

    private final Jdbi jdbi;
    private static JDBI INSTANCE;

    private JDBI(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://postgres-docker:5432/db");
        config.setUsername("docker");
        config.setPassword("docker");
        HikariDataSource ds = new HikariDataSource(config);
        jdbi = Jdbi.create(ds);
        jdbi.installPlugin(new SqlObjectPlugin());

    }

    public static JDBI getInstance(){
        if(JDBI.INSTANCE == null){
            JDBI.INSTANCE = new JDBI();
        }
        return INSTANCE;
    }

    public Jdbi jdbi(){
        return jdbi;
    }
}
