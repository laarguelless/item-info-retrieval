package org.laarguelless.services;

import org.laarguelless.domain.ItemRepository;
import org.laarguelless.json.Serializer;
import org.laarguelless.repository.ItemRepositoryImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/items")
public class ItemService {


    private final ItemRepository itemRepository = new ItemRepositoryImpl();

    @GET
    @Path("/{item_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getItemId(@PathParam("item_id") String itemId) {
        return Serializer.toJson(itemRepository.getById(itemId));
    }
}
