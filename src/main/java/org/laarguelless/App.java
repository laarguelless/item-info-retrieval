package org.laarguelless;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.leangen.geantyref.TypeToken;
import io.vavr.collection.List;
import io.vavr.gson.VavrGson;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.laarguelless.db.ItemConverter;
import org.laarguelless.db.JdbiClient;
import org.laarguelless.domain.Item;
import org.laarguelless.json.GsonBuilderFactory;
import org.laarguelless.json.GsonProvider;
import org.laarguelless.rest.ItemDtoConverter;
import org.laarguelless.rest.RestRepository;
import org.laarguelless.rest.RestRepositoryImpl;
import org.laarguelless.rest.clients.ChildrenRestClient;
import org.laarguelless.rest.clients.ItemRestClient;
import org.laarguelless.rest.dto.ChildDto;
import org.laarguelless.services.ItemService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.lang.reflect.Type;


public class App extends ResourceConfig {

    private static final Gson GSON = createGson();
    //For configuring the gson parsing for List of childDto
    private final Type listType = new TypeToken<List<ChildDto>>() {}.getType();

    private static Gson createGson() {
        GsonBuilder gsonBuilder = GsonBuilderFactory.GSON_BUILDER;
        VavrGson.registerAll(gsonBuilder);
        return gsonBuilder.create();
    }

    private static final String BASE_URL = System.getenv("BASE_URL");


    public App(){
        registerInstances(new GsonProvider<Item>());
        property(ServerProperties.MONITORING_STATISTICS_ENABLED, true);
        ItemConverter itemConverter = new ItemConverter(GSON::toJson,(json) -> GSON.fromJson(json,Item.class));
        JdbiClient jdbiClient = JdbiClient.create(itemConverter);
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(GsonProvider.class);
        Client client = ClientBuilder.newClient(clientConfig);
        ItemRestClient itemRestClient = new ItemRestClient(client,BASE_URL);
        ChildrenRestClient childrenRestClient = new ChildrenRestClient(client, BASE_URL,(json) -> GSON.fromJson(json,listType));
        ItemDtoConverter itemDtoConverter = new ItemDtoConverter();
        RestRepository restRepository = new RestRepositoryImpl(itemRestClient,childrenRestClient, itemDtoConverter);
        registerInstances(new ItemService(restRepository,jdbiClient));
    }

}
