package org.laarguelless;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.laarguelless.db.JdbiClient;
import org.laarguelless.domain.Item;
import org.laarguelless.json.GsonProvider;
import org.laarguelless.rest.ItemRestClient;
import org.laarguelless.services.ItemService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;


public class App extends ResourceConfig {

    private static final String ITEMS_URL = "https://api.mercadolibre.com/items/";

    public App(){
        registerInstances(new GsonProvider<Item>());
        JdbiClient jdbiClient = JdbiClient.create();
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(GsonProvider.class);
        Client client = ClientBuilder.newClient(clientConfig);
        ItemRestClient itemRestClient = new ItemRestClient(client, ITEMS_URL);
        registerInstances(new ItemService(itemRestClient,jdbiClient));
    }
}
