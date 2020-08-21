package org.laarguelless;

import com.google.gson.Gson;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.laarguelless.db.JdbiClient;
import org.laarguelless.domain.Item;
import org.laarguelless.json.GsonBuilderFactory;
import org.laarguelless.json.GsonProvider;
import org.laarguelless.rest.RestRepository;
import org.laarguelless.rest.RestRepositoryImpl;
import org.laarguelless.rest.clients.ChildrenRestClient;
import org.laarguelless.rest.clients.ItemRestClient;
import org.laarguelless.rest.services.HealthService;
import org.laarguelless.rest.services.ItemService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;


public class App extends ResourceConfig {

    private static final Gson GSON = GsonBuilderFactory.GSON;;

    private static final String BASE_URL = System.getenv("BASE_URL");


    public App(){
        registerInstances(new GsonProvider<Item>());
        property(ServerProperties.MONITORING_STATISTICS_ENABLED, true);
        JdbiClient jdbiClient = JdbiClient.create(GSON);
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(GsonProvider.class);
        Client client = ClientBuilder.newClient(clientConfig);
        ItemRestClient itemRestClient = new ItemRestClient(client,BASE_URL);
        ChildrenRestClient childrenRestClient = new ChildrenRestClient(client, BASE_URL,GSON);
        RestRepository restRepository = new RestRepositoryImpl(itemRestClient,childrenRestClient);
        registerInstances(new ItemService(restRepository,jdbiClient));
        registerClasses(HealthService.class);
    }

}
