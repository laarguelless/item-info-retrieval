package org.laarguelless.db;

import org.laarguelless.db.dao.ItemDao;
import org.laarguelless.domain.Item;

import java.util.function.Function;

public class ItemConverter {

    private final Function<Item,String> serializer;
    private final Function<String,Item> deserializer;


    public ItemConverter(Function<Item, String> serializer, Function<String, Item> deserializer) {
        this.deserializer = deserializer;
        this.serializer = serializer;
    }

    public Item fromDao(ItemDao dao){
        return deserializer.apply(dao.getResponse());
    }

    public ItemDao fromDomain(Item item){
        ItemDao itemDao = new ItemDao();
        itemDao.setRequest(item.getId());
        itemDao.setResponse(serializer.apply(item));
        return  itemDao;
    }
}
