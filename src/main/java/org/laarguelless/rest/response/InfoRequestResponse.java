package org.laarguelless.rest.response;

import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Gson.TypeAdapters
@Value.Immutable
public interface InfoRequestResponse {
    @SerializedName("status_code")
    int statusCode();
    long count();
}
