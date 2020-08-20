package org.laarguelless.db;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jdbi.v3.core.Jdbi;
import org.laarguelless.db.dao.ItemDao;

import java.util.Optional;

public class JdbiClient implements JdbiRepository{

    private final Jdbi jdbi;
    private final ItemConverter converter;

    JdbiClient(@NonNull Jdbi jdbi, ItemConverter converter){
        this.jdbi = jdbi;
        this.converter = converter;
    }

    public static JdbiClient create(ItemConverter itemConverter){
        return new JdbiClient(JdbiFactory.getInstance().jdbi(), itemConverter);
    }

    public void save(org.laarguelless.domain.Item item){
        jdbi.useHandle(handle -> {
            ItemDao dao = converter.fromDomain(item);
            handle.createUpdate("insert into items (request,response) values(:request,:response)")
                    .bind("request",dao.getRequest())
                    .bind("response",dao.getResponse())
                    .execute();
        });
    }

    public Optional<org.laarguelless.domain.Item> getById(String id){
       return jdbi.withHandle(handle ->
          handle.createQuery("Select * from items where request= :request")
                  .bind("request",id)
                  .mapToBean(ItemDao.class)
                  .findOne()
                  .map(converter::fromDao)
       );
    }

}
