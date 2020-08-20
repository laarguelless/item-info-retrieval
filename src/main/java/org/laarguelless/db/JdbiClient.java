package org.laarguelless.db;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jdbi.v3.core.Jdbi;

import java.util.Optional;

public class JdbiClient implements JdbiRepository{

    private final Jdbi jdbi;

    JdbiClient(@NonNull Jdbi jdbi){
        this.jdbi = jdbi;
    }

    public static JdbiClient create(){
        return new JdbiClient(JdbiFactory.getInstance().jdbi());
    }

    public void save(org.laarguelless.domain.Item item){
        jdbi.useExtension(ItemDAO.class, dao -> {
            dao.insert(Item.fromDomain(item));
        });
    }

    public Optional<org.laarguelless.domain.Item> getById(String id){
       return jdbi.withHandle(handle ->
          handle.createQuery("Select * from items where id = :id")
                  .bind("id",id)
                  .mapToBean(Item.class)
                  .findOne()
                  .map(Item::toDomain)
       );
    }

}
