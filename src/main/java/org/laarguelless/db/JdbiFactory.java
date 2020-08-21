package org.laarguelless.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

class JdbiFactory {

    private final Jdbi jdbi;
    private static JdbiFactory INSTANCE;

    private JdbiFactory(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(System.getenv("DB_DRIVER"));
        config.setJdbcUrl(System.getenv("DB_URL"));
        config.setUsername(System.getenv("DB_USER"));
        config.setPassword(System.getenv("DB_PASSWORD"));
        HikariDataSource ds = new HikariDataSource(config);
        jdbi = Jdbi.create(ds);
        jdbi.installPlugin(new SqlObjectPlugin());

    }

    public static JdbiFactory getInstance(){
        if(JdbiFactory.INSTANCE == null){
            JdbiFactory.INSTANCE = new JdbiFactory();
        }
        return INSTANCE;
    }

    public Jdbi jdbi(){
        return jdbi;
    }
}
