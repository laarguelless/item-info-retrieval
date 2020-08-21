package org.laarguelless.rest.services;

import org.glassfish.jersey.server.monitoring.MonitoringStatistics;
import org.glassfish.jersey.server.monitoring.TimeWindowStatistics;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/health")
public class HealthService {
    @Inject
    Provider<MonitoringStatistics> monitoringStatisticsProvider;

    @GET
    public Response health(){
        final TimeWindowStatistics timeWindowStatistics = monitoringStatisticsProvider
                .get()
                .getResourceClassStatistics().get(ItemService.class)
                .getRequestExecutionStatistics()
                .getTimeWindowStatistics()
                .get(60000L);
        return Response.ok(timeWindowStatistics.getRequestCount()).build();
    }
}
