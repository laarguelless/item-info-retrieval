package org.laarguelless.rest;

import org.laarguelless.domain.Item;
import org.laarguelless.json.GsonFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ItemRestClient {

    private final Client client = ClientBuilder.newClient();
    private final static String URL = "https://api.mercadolibre.com/items/";

    public Item getItemById(String itemId){
        Response response =  client
                .target(URL)
                .path(itemId)
                .request(MediaType.APPLICATION_JSON)
                .get();
        String json = response.readEntity(String.class);
        return GsonFactory.gson().fromJson(json,Item.class);
    }
}
