package org.laarguelless.rest.clients;

import io.vavr.collection.List;
import org.laarguelless.rest.dto.ChildDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.util.function.Function;

public class ChildrenRestClient extends RestClient<List<ChildDto>> {

    private final Function<String,List<ChildDto>> deserializer;


    public ChildrenRestClient(Client client, String url, Function<String,List<ChildDto>> deserializer){
        super(client,String.format("%s/{id}/children", url));
        this.deserializer = deserializer;
    }

    @Override
    List<ChildDto> parseValueFromResponse(Response response) {
        String stringEntity = response.readEntity(String.class);
        return deserializer.apply(stringEntity);

    }

    @Override
    List<ChildDto> getEmptyValue() {
        return List.empty();
    }
}
