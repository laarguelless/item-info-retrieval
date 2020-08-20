package org.laarguelless.rest.dto;

import java.math.BigInteger;
import java.time.OffsetDateTime;

public class ItemDto {
    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public BigInteger getPrice() {
        return price;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public OffsetDateTime getStopTime() {
        return stopTime;
    }

    private String categoryId;
    private BigInteger price;
    private OffsetDateTime startTime;
    private OffsetDateTime stopTime;
}
