package org.laarguelless.repository;

import org.laarguelless.domain.Item;
import org.laarguelless.domain.ItemRepository;
import org.laarguelless.json.Serializer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ItemRepositoryImpl implements ItemRepository{

    private final Client client = ClientBuilder.newClient();
    private final static String URL = "https://api.mercadolibre.com/items/";

    @Override
    public Item getById(String itemId) {
        Response response =  client
                .target(URL)
                .path(itemId)
                .request(MediaType.APPLICATION_JSON)
                .get();
        String json = response.readEntity(String.class);
        return Serializer.gson().fromJson(json,Item.class);
    }
}
