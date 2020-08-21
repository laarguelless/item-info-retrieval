package org.laarguelless.db;

import com.google.gson.Gson;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jdbi.v3.core.Jdbi;
import org.laarguelless.db.dao.ItemDao;
import org.laarguelless.db.dao.ResponseDao;
import org.laarguelless.domain.Item;

import java.util.Optional;

public class JdbiClient implements JdbiRepository{

    private final Jdbi jdbi;
    private final Gson gson;

    JdbiClient(@NonNull Jdbi jdbi, Gson gson){
        this.jdbi = jdbi;
        this.gson = gson;
    }

    public static JdbiClient create(Gson gson){
        return new JdbiClient(JdbiFactory.getInstance().jdbi(), gson);
    }

    ItemDao deserialize(String json){
        return gson.fromJson(json,ItemDao.class);
    }

    String serialize(ItemDao itemDao){
        return gson.toJson(itemDao);
    }

    public void save(Item item){
        jdbi.useHandle(handle -> {
            ItemDao dao = ItemDao.fromDomain(item);
            handle.createUpdate("insert into items (request,response) values(:request,:response)")
                    .bind("request",dao.id())
                    .bind("response",serialize(dao))
                    .execute();
        });
    }

    public Optional<Item> getById(String id){
       return jdbi.withHandle(handle ->
          handle.createQuery("Select * from items where request= :request")
                   .bind("request",id)
                   .mapToBean(ResponseDao.class)
                   .findOne()
                   .map(ResponseDao::response)
                   .map(this::deserialize)
                   .map(ItemDao::toDomain));
    }

}
