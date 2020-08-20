package org.laarguelless.rest.dto;

import java.time.OffsetDateTime;
import java.util.Objects;

public class ChildDto {

    private String id;
    private OffsetDateTime stopTime;


    public String getId() {
        return id;
    }

    public OffsetDateTime getStopTime() {
        return stopTime;
    }

    public void id(String id){
        this.id = id;
    }

    public void stopTime(OffsetDateTime stopTime){
        this.stopTime = stopTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildDto childDto = (ChildDto) o;
        return Objects.equals(id, childDto.id) &&
                Objects.equals(stopTime, childDto.stopTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stopTime);
    }
}
