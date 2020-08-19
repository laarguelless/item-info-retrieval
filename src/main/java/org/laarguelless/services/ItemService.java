package org.laarguelless.services;

import org.laarguelless.domain.Item;
import org.laarguelless.domain.ItemRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.function.Function;

@Path("/items")
public class ItemService {

    private final ItemRepository itemRepository;
    private final Function<Item,String> serializer;

    public ItemService(ItemRepository itemRepository, Function<Item,String> serializer) {
        this.itemRepository = itemRepository;
        this.serializer = serializer;
    }

    @GET
    @Path("/{item_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemId(@PathParam("item_id") String itemId) {
        Item item = itemRepository.getById(itemId);
        itemRepository.saveItem(item);
        return Response
                .ok(serializer.apply(item))
                .build();
    }
}
