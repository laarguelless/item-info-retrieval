package org.laarguelless.db;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jdbi.v3.core.Jdbi;
import org.laarguelless.domain.Item;

import java.util.function.Function;

public class JDBIClient {

    private final Jdbi jdbi;
    private final Function<Item,String> serializer;


    public JDBIClient(@NonNull Jdbi jdbi,@NonNull Function<Item,String> serializer){
        this.jdbi = jdbi;
        this.serializer = serializer;
    }

    public void cacheItem(Item item){
        jdbi.useExtension(ItemDAO.class, dao -> {
            String request = item.id();
            String response = serializer.apply(item);
            dao.insert(request,response);
        });
    }
}
