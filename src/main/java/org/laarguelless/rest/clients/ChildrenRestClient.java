package org.laarguelless.rest.clients;

import com.google.gson.Gson;
import io.leangen.geantyref.TypeToken;
import io.vavr.collection.List;
import org.laarguelless.rest.clients.dto.ChildDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;

public class ChildrenRestClient extends RestClient<List<ChildDto>> {

    private final Gson gson;

    /** For configuring the gson parsing for List of childDto **/
    private final Type listType = new TypeToken<List<ChildDto>>() {}.getType();

    public ChildrenRestClient(Client client, String url, Gson gson){
        super(client,String.format("%s/{id}/children", url));
        this.gson = gson;
    }

    @Override
    List<ChildDto> parseValueFromResponse(Response response) {
        String stringEntity = response.readEntity(String.class);
        return gson.fromJson(stringEntity,listType);

    }

    @Override
    List<ChildDto> getEmptyValue() {
        return List.empty();
    }
}
