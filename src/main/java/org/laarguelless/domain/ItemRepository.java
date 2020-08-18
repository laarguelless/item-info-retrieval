package org.laarguelless.domain;

public interface ItemRepository {

    Item getById(String itemId);
    void saveItem(Item item);
}
