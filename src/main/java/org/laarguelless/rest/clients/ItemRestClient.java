package org.laarguelless.rest.clients;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.laarguelless.rest.dto.ItemDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.util.Optional;


public class ItemRestClient extends RestClient<Optional<ItemDto>> {

    public ItemRestClient(@NonNull Client client, String baseUrl){
        super(client,String.format("%s/{id}",baseUrl));
    }

    @Override
    Optional<ItemDto> parseValueFromResponse(Response response) {
        return Optional.ofNullable(response.readEntity(ItemDto.class));
    }

    @Override
    Optional<ItemDto> getEmptyValue() {
        return Optional.empty();
    }

}
