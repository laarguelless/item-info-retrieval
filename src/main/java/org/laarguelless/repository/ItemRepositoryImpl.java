package org.laarguelless.repository;

import com.google.gson.Gson;
import org.laarguelless.db.JDBIClient;
import org.laarguelless.domain.Item;
import org.laarguelless.domain.ItemRepository;
import org.laarguelless.rest.ItemRestClient;

public class ItemRepositoryImpl implements ItemRepository {

    private final ItemRestClient itemRestClient;
    private final JDBIClient jdbiClient;
    private final Gson gson;

    public ItemRepositoryImpl(ItemRestClient itemRestClient, JDBIClient jdbiClient, Gson gson) {
        this.itemRestClient = itemRestClient;
        this.jdbiClient = jdbiClient;
        this.gson = gson;
    }

    @Override
    public Item getById(String itemId) {
        return this.itemRestClient.getItemById(itemId);
    }

    @Override
    public void saveItem(Item item) {
        String request = item.id();
        String response = gson.toJson(item);
        jdbiClient.cacheItem(request,response);
    }
}
