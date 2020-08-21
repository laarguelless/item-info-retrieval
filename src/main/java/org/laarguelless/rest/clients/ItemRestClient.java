package org.laarguelless.rest.clients;

import com.google.gson.Gson;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.laarguelless.rest.clients.dto.ItemDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.util.Optional;


public class ItemRestClient extends RestClient<Optional<ItemDto>> {

    private final Gson gson;

    public ItemRestClient(@NonNull Client client, String baseUrl, Gson gson){
        super(client,String.format("%s/{id}",baseUrl));
        this.gson = gson;
    }

    @Override
    Optional<ItemDto> parseValueFromResponse(Response response) {
        String json = response.readEntity(String.class);
        return Optional.ofNullable(gson.fromJson(json,ItemDto.class));
    }

    @Override
    Optional<ItemDto> getEmptyValue() {
        return Optional.empty();
    }

}
