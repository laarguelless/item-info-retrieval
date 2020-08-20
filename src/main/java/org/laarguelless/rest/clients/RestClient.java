package org.laarguelless.rest.clients;

import org.checkerframework.checker.nullness.qual.NonNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.vavr.API.*;

public abstract class RestClient<T> {

    private final WebTarget target;
    private final static String MEDIA_TYPE =  MediaType.APPLICATION_JSON;

    protected RestClient(@NonNull Client client, @NonNull String url){
        this.target = client.target(url);
    }

    public  T getById(String id){
        Response response =  this.target
                .resolveTemplate("id",id)
                .request(MEDIA_TYPE)
                .get();
        return getValueFromResponse(response);
    };

    private T getValueFromResponse(Response response){
        return Match(response.getStatus()).of(
                Case($(200), this.parseValueFromResponse(response)),
                Case($(), this::getEmptyValue)
        );
    }

    abstract T parseValueFromResponse(Response response);

    abstract T getEmptyValue();
}
