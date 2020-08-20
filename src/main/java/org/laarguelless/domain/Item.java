package org.laarguelless.domain;

import lombok.Data;

import java.math.BigInteger;
import java.time.OffsetDateTime;

public class Item {

    private final String id;
    private final String title;
    private final String categoryId;

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

    private final BigInteger price;
    private final OffsetDateTime startTime;
    private final OffsetDateTime stopTime;

    public Item(String id, String title, String categoryId, BigInteger price, OffsetDateTime startTime,
                OffsetDateTime stopTime) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.price = price;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }
}
