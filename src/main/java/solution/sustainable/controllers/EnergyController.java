package solution.sustainable.controllers;

import com.google.inject.Inject;
import solution.sustainable.dao.EnergyRepository;
import solution.sustainable.models.Energy;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by aditya.dalal on 26/04/18.
 */

@Path("/v1/energies")
public class EnergyController {

    @Inject
    private EnergyRepository energyRepository;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Energy addEnergy(Energy energy) {
        return energyRepository.insert(energy);
    }
}
