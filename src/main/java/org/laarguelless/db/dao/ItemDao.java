package org.laarguelless.db.dao;

import lombok.Data;

@Data
public class ItemDao {

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private String request;
    private String response;

}