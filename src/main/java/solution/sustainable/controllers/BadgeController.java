package solution.sustainable.controllers;

import com.google.inject.Inject;
import solution.sustainable.exceptions.Error;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Badge;
import solution.sustainable.services.BadgeService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by aditya.dalal on 28/04/18.
 */

@Path("/v1/badges")
public class BadgeController {

    @Inject
    private BadgeService badgeService;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBadge(Badge badge) {
        Badge result = null;
        try {
            validateBadge(badge);
            result = badgeService.add(badge);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(Error.newError(e)).build();
        }
        return Response.status(201).entity(result).build();
    }

    private void validateBadge(Badge badge) throws InvalidRequestException {
        if (badge.getName() == null || badge.getName().trim().equals(""))
            throw new InvalidRequestException(400, "Badge name cannot be null/empty");
    }

}
