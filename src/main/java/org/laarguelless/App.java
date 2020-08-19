package org.laarguelless;

import org.glassfish.jersey.server.ResourceConfig;
import org.laarguelless.db.JDBI;
import org.laarguelless.db.JDBIClient;
import org.laarguelless.domain.Item;
import org.laarguelless.domain.ItemRepository;
import org.laarguelless.json.Serializer;
import org.laarguelless.json.SerializerImpl;
import org.laarguelless.repository.ItemRepositoryImpl;
import org.laarguelless.rest.ItemRestClient;
import org.laarguelless.services.ItemService;

import javax.ws.rs.client.ClientBuilder;


public class App extends ResourceConfig {

    private static final String ITEMS_URL = "https://api.mercadolibre.com/items/";

    public App(){
        Serializer<Item> itemSerializer = new SerializerImpl<>(Item.class);
        JDBIClient jdbiClient = new JDBIClient(JDBI.getInstance().jdbi(), itemSerializer::toJson);
        ItemRestClient itemRestClient = new ItemRestClient(ClientBuilder.newClient(), ITEMS_URL, itemSerializer::fromJson);
        ItemRepository itemRepository = new ItemRepositoryImpl(itemRestClient,jdbiClient);
        registerInstances(new ItemService(itemRepository, itemSerializer::toJson));
    }
}
