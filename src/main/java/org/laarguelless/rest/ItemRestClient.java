package org.laarguelless.rest;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.laarguelless.domain.Item;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static io.vavr.API.*;


public class ItemRestClient implements RestRepository {

    private final WebTarget target;

    public ItemRestClient(@NonNull Client client, @NonNull String url){
        this.target = client.target(url);
    }

    public Optional<org.laarguelless.domain.Item> getById(String itemId){
        Response response =  target.path(itemId)
                .request(MediaType.APPLICATION_JSON)
                .get();
        return getItemFromResponse(response);
    }

    private Optional<org.laarguelless.domain.Item> getItemFromResponse(Response response){
        return Match(response.getStatus()).of(
                Case($(200),Optional.ofNullable(response.readEntity(Item.class))),
                Case($(), Optional.empty())
        );
    }

}
