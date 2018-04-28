package solution.sustainable.controllers;

import com.google.inject.Inject;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.*;
import solution.sustainable.models.Error;
import solution.sustainable.services.DeviceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
            result = deviceService.register(device);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(getError(e)).build();
        }
        return Response.status(201).entity(result).build();
    }

    @Path("/{deviceId}/goals/suggest")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response suggestGoals(@PathParam("deviceId") String deviceId) {
        List<GoalTemplate> goalTemplates = null;
        try {
            goalTemplates = deviceService.suggestGoals(deviceId);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(getError(e)).build();
        }
        return Response.ok().entity(goalTemplates).build();
    }

    @Path("/{deviceId}/goals")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGoal(@PathParam("deviceId") String deviceId, Goal goal) {
        Goal result = null;
        try {
            result = deviceService.addGoal(deviceId, goal);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(getError(e)).build();
        }
        return Response.ok().entity(result).build();
    }

    @Path("/{deviceId}/goals")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGoals(@PathParam("deviceId") String deviceId) {
        List<Goal> goals = null;
        try {
            goals = deviceService.getGoals(deviceId);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(getError(e)).build();
        }
        return Response.ok().entity(goals).build();
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
            return Response.status(e.getStatus()).entity(getError(e)).build();
        }
        return Response.status(201).entity(result).build();
    }

    private Error getError(InvalidRequestException e) {
        return new Error(e.getStatus(), e.getMessage());
    }
}
