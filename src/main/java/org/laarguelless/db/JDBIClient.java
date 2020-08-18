package org.laarguelless.db;

import org.jdbi.v3.core.Jdbi;

public class JDBIClient {

    private final Jdbi jdbi;

    public JDBIClient(Jdbi jdbi){
        this.jdbi = jdbi;
    }

    public void cacheItem(String request, String response){
        jdbi.useExtension(ItemDAO.class, dao -> {
            dao.insert(request,response);
        });
    }
}
