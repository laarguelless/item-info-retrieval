package org.laarguelless.rest;

import io.vavr.collection.List;
import org.laarguelless.domain.Item;
import org.laarguelless.rest.clients.ChildrenRestClient;
import org.laarguelless.rest.clients.ItemRestClient;
import org.laarguelless.rest.dto.ChildDto;
import org.laarguelless.rest.dto.ItemDto;
import java.util.Optional;

public class RestRepositoryImpl implements RestRepository{

    private final ItemRestClient itemRestClient;
    private final ChildrenRestClient childrenRestClient;
    private final ItemDtoConverter itemConverter;

    public RestRepositoryImpl(ItemRestClient itemRestClient, ChildrenRestClient childrenRestClient,
                              ItemDtoConverter itemConverter) {
        this.itemRestClient = itemRestClient;
        this.childrenRestClient = childrenRestClient;
        this.itemConverter = itemConverter;
    }

    @Override
    public Optional<Item> getById(String itemId) {
        Optional<ItemDto> maybeItemDto = itemRestClient.getById(itemId);
        return maybeItemDto.map(itemDto -> getChildren(itemId, itemDto));
    }

    private Item getChildren(String itemId, ItemDto itemDto){
        List<ChildDto> children = childrenRestClient.getById(itemId);
        return itemConverter.fromDto(itemDto,children);
    }
}
