package org.laarguelless.domain;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Child {
    private final String itemId;
    private final OffsetDateTime stopTime;

    public Child(String itemId, OffsetDateTime stopTime) {
        this.itemId = itemId;
        this.stopTime = stopTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return Objects.equals(itemId, child.itemId) &&
                Objects.equals(stopTime, child.stopTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, stopTime);
    }
}
