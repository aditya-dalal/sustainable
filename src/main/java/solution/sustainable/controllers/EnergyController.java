package solution.sustainable.controllers;

import com.google.inject.Inject;
import solution.sustainable.exceptions.Error;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Energy;
import solution.sustainable.services.EnergyService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by aditya.dalal on 26/04/18.
 */

@Path("/v1/energies")
public class EnergyController {

    @Inject
    private EnergyService energyService;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEnergy(Energy energy) {
        Energy result = null;
        try {
            validateEnergy(energy);
            result = energyService.add(energy);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(Error.newError(e)).build();
        }
        return Response.status(201).entity(result).build();
    }

    private void validateEnergy(Energy energy) throws InvalidRequestException {
        if(energy.getName() == null || energy.getName().trim().equals(""))
            throw new InvalidRequestException(400, "Energy name cannot be null/empty");
        if(energy.getType() == null || energy.getType().trim().equals(""))
            throw new InvalidRequestException(400, "Energy type cannot be null/empty");
        if(energy.getUnit() == null || energy.getUnit().trim().equals(""))
            throw new InvalidRequestException(400, "Energy unit cannot be null/empty");
        if(energy.getCost() == null || energy.getCost() < 0)
            throw new InvalidRequestException(400, "Invalid energy cost: " + energy.getCost());
    }
}
