package com.gottaeat.microservices.location.driver.api;

import com.gottaeat.microservices.location.driver.domain.DriverLocation;
import com.gottaeat.microservices.location.driver.domain.DriverPositionSignal;
import com.gottaeat.microservices.location.driver.repository.DriverLocationRepository;
import com.gottaeat.microservices.location.driver.service.DriverLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Collections;
import java.util.List;

@Path("/location/driver")
public class DriverLocationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverLocationResource.class);

    @Inject
    DriverLocationService service;

    @Inject
    DriverLocationRepository repository;

    @POST
    public void recordSignal(DriverPositionSignal signal) {
        LOGGER.info("Received signal: {}", signal);
        service.record(signal);
    }

    @GET
    public List<DriverLocation> list() {
        List<DriverLocation> locations = repository.list();
        Collections.sort(locations, Collections.reverseOrder());
        return locations;
    }

    @Path("/{driverId}")
    @GET
    public List<DriverLocation> getByDriverId(@PathParam("driverId") long driverId) {
        List<DriverLocation> locations = repository.getByDriverId(driverId);
        Collections.sort(locations, Collections.reverseOrder());
        return locations;
    }

}
