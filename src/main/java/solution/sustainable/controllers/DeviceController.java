package solution.sustainable.controllers;

import com.google.inject.Inject;
import solution.sustainable.exceptions.Error;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Consumption;
import solution.sustainable.models.Device;
import solution.sustainable.models.Goal;
import solution.sustainable.models.TrackEnergy;
import solution.sustainable.services.DeviceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by aditya.dalal on 25/04/18.
 */

@Path("/v1/devices")
public class DeviceController {

    @Inject
    private DeviceService deviceService;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Device device) {
        if(device.getId() == null)
            return Response.status(400).entity(new Error(400, "Missing required field: id")).build();
        Device result = null;
        try {
            result = deviceService.register(device);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(Error.newError(e)).build();
        }
        return Response.status(201).entity(result).build();
    }


    @Path("/{deviceId}/consumption")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addConsumption(@PathParam("deviceId") String deviceId, Consumption consumption) {
        TrackEnergy result = null;
        try {
            result = deviceService.addConsumption(deviceId, consumption);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(Error.newError(e)).build();
        }
        return Response.status(201).entity(result).build();
    }

}
