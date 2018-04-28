package solution.sustainable.controllers;

import com.google.inject.Inject;
import solution.sustainable.exceptions.Error;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Owner;
import solution.sustainable.services.OwnerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by aditya.dalal on 25/04/18.
 */

@Path("/v1/owners")
public class OwnerController {

    @Inject
    private OwnerService ownerService;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Owner owner) {
        Owner result = null;
        try {
            validateOwner(owner);
            result = ownerService.register(owner);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(Error.newError(e)).build();
        }
        return Response.status(201).entity(result).build();
    }

    @Path("/{ownerId}/devices/{deviceId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerDevice(@PathParam("ownerId") String ownerId, @PathParam("deviceId") String deviceId) {
        boolean result;
        try {
            result = ownerService.addDevice(ownerId, deviceId);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(Error.newError(e)).build();
        }
        if(result)
            return Response.status(204).build();
        return Response.status(500).entity(new Error(500, "Failed to add device")).build();
    }

    @Path("/{ownerId}/badges/{badgeId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBadge(@PathParam("ownerId") String ownerId, @PathParam("badgeId") String badgeId) {
        boolean result;
        try {
            result = ownerService.addBadge(ownerId, badgeId);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(Error.newError(e)).build();
        }
        if(result)
            return Response.status(204).build();
        return Response.status(500).entity(new Error(500, "Failed to add badge")).build();
    }

    private void validateOwner(Owner owner) throws InvalidRequestException {
        if(owner.getFirstName() == null || owner.getFirstName().equals(""))
            throw new InvalidRequestException(400, "First name cannot be null/empty");
        if(owner.getLastName() == null || owner.getLastName().equals(""))
            throw new InvalidRequestException(400, "Last name cannot be null/empty");
    }
}
