package org.laarguelless.rest.response;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ItemServiceException extends WebApplicationException {

    public ItemServiceException(int status, String message){
        super(Response.status(status).entity(message).build());
    }
}
