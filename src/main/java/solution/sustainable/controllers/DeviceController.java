package solution.sustainable.controllers;

import com.google.inject.Inject;
import solution.sustainable.exceptions.Error;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Consumption;
import solution.sustainable.models.Device;
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
        Device result = null;
        try {
            validateDevice(device);
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
            validateConsumption(consumption);
            result = deviceService.addConsumption(deviceId, consumption);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(Error.newError(e)).build();
        }
        return Response.status(201).entity(result).build();
    }

    private void validateConsumption(Consumption consumption) throws InvalidRequestException {
        if(consumption.getValue() == null || consumption.getValue() < 0)
            throw new InvalidRequestException(400, "Value cannot be null/negative");
    }

    private void validateDevice(Device device) throws InvalidRequestException {
        if (device.getId() == null || device.getId().trim().equals(""))
            throw new InvalidRequestException(400, "DeviceId cannot be null/empty");
        if (device.getName() == null || device.getName().trim().equals(""))
            throw new InvalidRequestException(400, "Device name cannot be null/empty");
        if (device.getEnergyId() == null || device.getEnergyId().trim().equals(""))
            throw new InvalidRequestException(400, "EnergyId cannot be null/empty");
    }

}
