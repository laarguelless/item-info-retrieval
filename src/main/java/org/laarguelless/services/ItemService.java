package org.laarguelless.services;

import org.laarguelless.db.JdbiRepository;
import org.laarguelless.domain.Item;
import org.laarguelless.rest.EmptyResponse;
import org.laarguelless.rest.RestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/items")
public class ItemService {

    private final RestRepository restRepository;
    private final JdbiRepository jdbiRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ItemService(RestRepository restRepository, JdbiRepository jdbiRepository) {
        this.restRepository = restRepository;
        this.jdbiRepository = jdbiRepository;
    }

    @GET
    @Path("/{item_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemId(@PathParam("item_id") String itemId) {
        return jdbiRepository.getById(itemId)
                .map(this::fromItem)
                .orElseGet(()->this.getItemFromService(itemId));
    }

    private Response getItemFromService(String itemId){
        logger.info("Item not found in DB");
        return restRepository.getById(itemId).map(item -> {
            jdbiRepository.save(item);
            return fromItem(item);
        }).orElseGet(this::notFound);
    }

    private Response fromItem(Item item){
        return  Response
                .ok(item)
                .build();
    }

    private Response notFound(){
        return Response.ok(new EmptyResponse()).build();
    }
}
