package org.laarguelless.domain;

import java.math.BigInteger;
import java.time.OffsetDateTime;

public class Item {

    private final String id;
    private final String title;
    private final String categoryId;
    private final BigInteger price;
    private final OffsetDateTime startTime;
    private final OffsetDateTime stopTime;

    public Item(String id, String title, String categoryId, BigInteger price,
                OffsetDateTime startTime, OffsetDateTime stopTime) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.price = price;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }
}
