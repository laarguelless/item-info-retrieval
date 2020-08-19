package org.laarguelless.rest;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.laarguelless.domain.Item;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.function.Function;

public class ItemRestClient {

    private final WebTarget target;
    private final  Function<String,Item> deserializer;

    public ItemRestClient(@NonNull Client client, @NonNull String url,@NonNull Function<String,Item> deserializer){
        this.target = client.target(url);
        this.deserializer = deserializer;
    }

    public Item getItemById(String itemId){
        Response response =  target.path(itemId)
                .request(MediaType.APPLICATION_JSON)
                .get();
        String json = response.readEntity(String.class);
        return deserializer.apply(json);
    }
}
