package org.laarguelless.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface ItemDAO {

    @SqlQuery("Select response from items_cache where request = :id")
    String getItemJson(@Bind("id") String id);

    @SqlUpdate("insert into items_cache (request,response) values(:request,:response)")
    void insert(@Bind("request") String request, @Bind("response") String response);
}

