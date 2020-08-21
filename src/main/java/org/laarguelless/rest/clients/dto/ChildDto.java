package org.laarguelless.rest.clients.dto;

import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.time.OffsetDateTime;

@Gson.TypeAdapters
@Value.Immutable
public abstract class ChildDto {

    public abstract String id();
    @SerializedName("stop_time")
    public abstract OffsetDateTime stopTime();

}
