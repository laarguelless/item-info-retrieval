package org.laarguelless.db;

import lombok.Data;

import java.math.BigInteger;
import java.time.OffsetDateTime;

@Data
public class Item {

    private String id;
    private String title;
    private String categoryId;
    private BigInteger price;
    private OffsetDateTime startTime;
    private OffsetDateTime stopTime;

    public org.laarguelless.domain.Item toDomain(){
        return new org.laarguelless.domain.Item(id,title,categoryId,price,startTime,stopTime);
    }

    public static Item fromDomain(org.laarguelless.domain.Item item){
        Item dto = new Item();
        dto.id = item.getId();
        dto.title = item.getTitle();
        dto.categoryId = item.getCategoryId();
        dto.price = item.getPrice();
        dto.startTime = item.getStartTime();
        dto.stopTime = item.getStopTime();
        return dto;
    }

}