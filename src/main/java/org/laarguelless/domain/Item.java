package org.laarguelless.domain;

import io.vavr.collection.List;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.Objects;

public class Item {

    private final String id;
    private final String title;
    private final String categoryId;
    private final BigInteger price;
    private final OffsetDateTime startTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(title, item.title) &&
                Objects.equals(categoryId, item.categoryId) &&
                Objects.equals(price, item.price) &&
                Objects.equals(startTime, item.startTime) &&
                Objects.equals(stopTime, item.stopTime) &&
                Objects.equals(children, item.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, categoryId, price, startTime, stopTime, children);
    }

    private final OffsetDateTime stopTime;
    private final List<Child> children;

    public String getId() {
        return id;
    }

    public static Builder builder(){return new Builder();}

    private Item(Builder builder){
        this.id = builder.id;
        this.title = builder.title;
        this.categoryId = builder.categoryId;
        this.price = builder.price;
        this.startTime = builder.startTime;
        this.stopTime = builder.stopTime;
        this.children = builder.children;
    }

    public static class Builder {
        private String id;
        private String title;
        private String categoryId;
        private BigInteger price;
        private OffsetDateTime startTime;
        private OffsetDateTime stopTime;
        private List<Child> children;

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder categoryId(String categoryId){
            this.categoryId = categoryId;
            return this;
        }

        public Builder price(BigInteger price){
            this.price = price;
            return this;
        }

        public Builder startTime(OffsetDateTime startTime){
            this.startTime = startTime;
            return this;
        }

        public Builder stopTime(OffsetDateTime stopTime){
            this.stopTime = stopTime;
            return  this;
        }

        public Builder children(List<Child> children){
            this.children = children;
            return this;
        }

        public Item build(){
            return new Item(this);
        }
    }
}
