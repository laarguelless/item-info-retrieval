package org.laarguelless.rest.services;

import io.vavr.collection.List;
import org.glassfish.jersey.server.monitoring.MonitoringStatistics;
import org.glassfish.jersey.server.monitoring.TimeWindowStatistics;
import org.laarguelless.rest.response.ImmutableInfoRequestResponse;
import org.laarguelless.rest.response.ImmutableMetricsResponse;
import org.laarguelless.rest.response.InfoRequestResponse;
import org.laarguelless.rest.response.MetricsResponse;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.time.OffsetDateTime;

@Path("/health")
public class HealthService {
    @Inject
    Provider<MonitoringStatistics> monitoringStatisticsProvider;
    private final static long ONE_MINUTE_MS = 60000l;

    @GET
    public Response health(){

        final TimeWindowStatistics timeWindowStatistics = monitoringStatisticsProvider
                .get()
                .getRequestStatistics()
                .getTimeWindowStatistics()
                .get(ONE_MINUTE_MS);

        OffsetDateTime date = OffsetDateTime.now();
        long averageResponseTime = timeWindowStatistics.getAverageDuration();
        long totalRequest = timeWindowStatistics.getRequestCount();
        List<InfoRequestResponse> infoRequests = monitoringStatisticsProvider
                .get()
                .getResponseStatistics()
                .getResponseCodes()
                .entrySet()
                .stream()
                .map(entry-> ImmutableInfoRequestResponse
                        .builder()
                        .statusCode(entry.getKey())
                        .count(entry.getValue())
                        .build())
                .collect(List.collector());
        MetricsResponse response = ImmutableMetricsResponse.builder()
                .date(date)
                .avgResponseTime(averageResponseTime)
                .totalRequest(totalRequest)
                .infoRequests(infoRequests)
                .build();
        return Response.ok(response).build();
    }
}
