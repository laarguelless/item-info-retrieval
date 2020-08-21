package org.laarguelless.rest.response;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import org.laarguelless.json.GsonBuilderFactory;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MetricsResponseTest {

    @Test
    void serialize(){
        ImmutableMetricsResponse metricsResponse = ImmutableMetricsResponse.builder()
                .date(OffsetDateTime.parse("2019-03-02T20:31:02Z"))
                .avgResponseTime(100)
                .totalRequest(12)
                .infoRequests(List.of(
                        ImmutableInfoRequestResponse
                                .builder()
                                .count(2)
                                .statusCode(200)
                                .build())).build();
        String json = "{\"date\":\"2019-03-02T20:31:02Z\",\"avg_response_time\":100,\"total_requests\":12,\"info_request\":[{\"status_code\":200,\"count\":2}]}";
        assertEquals(json, GsonBuilderFactory.GSON.toJson(metricsResponse));
    }
}