package org.laarguelless.rest.response;

import com.google.gson.annotations.SerializedName;
import io.vavr.collection.List;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.time.OffsetDateTime;

@Gson.TypeAdapters
@Value.Immutable
public interface MetricsResponse {
    OffsetDateTime date();
    @SerializedName("avg_response_time")
    long avgResponseTime();
    @SerializedName("total_reqeusts")
    long totalRequest();
    @SerializedName("info_request")
    List<InfoRequestResponse> infoRequests();
}
