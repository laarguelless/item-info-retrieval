package org.laarguelless.db.dao;

import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.time.OffsetDateTime;

@Gson.TypeAdapters
@Value.Immutable
public abstract class ChildDao {
    public abstract String itemId();
    @SerializedName("stop_time")
    public abstract OffsetDateTime stopTime();
}
