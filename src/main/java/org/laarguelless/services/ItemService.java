package org.laarguelless.services;

import org.laarguelless.db.DataSource;
import org.laarguelless.db.JDBIClient;
import org.laarguelless.domain.Item;
import org.laarguelless.domain.ItemRepository;
import org.laarguelless.json.GsonFactory;
import org.laarguelless.repository.ItemRepositoryImpl;
import org.laarguelless.rest.ItemRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/items")
public class ItemService {

    private static final ItemRestClient itemRestClient = new ItemRestClient();
    private static final JDBIClient jdbiClient = new JDBIClient(DataSource.jdbi());
    private static final ItemRepository itemRepository = new ItemRepositoryImpl(itemRestClient,jdbiClient, GsonFactory.gson());

    @GET
    @Path("/{item_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getItemId(@PathParam("item_id") String itemId) {
        Item item = itemRepository.getById(itemId);
        itemRepository.saveItem(item);
        return GsonFactory.gson().toJson(itemRepository.getById(itemId));
    }
}
