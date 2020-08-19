package org.laarguelless.repository;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.laarguelless.db.JDBIClient;
import org.laarguelless.domain.Item;
import org.laarguelless.domain.ItemRepository;
import org.laarguelless.rest.ItemRestClient;

public class ItemRepositoryImpl implements ItemRepository {

    private final ItemRestClient itemRestClient;
    private final JDBIClient jdbiClient;

    public ItemRepositoryImpl(@NonNull ItemRestClient itemRestClient, @NonNull JDBIClient jdbiClient) {
        this.itemRestClient = itemRestClient;
        this.jdbiClient = jdbiClient;
    }

    @Override
    public Item getById(String itemId) {
        return this.itemRestClient.getItemById(itemId);
    }

    @Override
    public void saveItem(Item item) {
        jdbiClient.cacheItem(item);
    }
}
