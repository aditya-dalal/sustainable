package solution.sustainable.controllers;

import com.google.inject.Inject;
import solution.sustainable.models.GoalTemplate;
import solution.sustainable.models.GoalType;
import solution.sustainable.services.GoalService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by aditya.dalal on 26/04/18.
 */

@Path("/v1/goals")
public class GoalController {

    @Inject
    private GoalService goalService;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GoalTemplate addGoalTemplate(GoalTemplate goalTemplate) {
        return goalService.addGoalTemplate(goalTemplate);
    }

    @Path("/")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<GoalTemplate> getGoalsForEnergyType(@QueryParam("energyType") String energyType) {
        return goalService.getGoalsForEnergyType(energyType);
    }

    @Path("/types")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GoalType addGoalType(GoalType goalType) {
        return goalService.addGoalType(goalType);
    }

    @Path("/types")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<GoalType> getGoalTypes() {
        return goalService.getGoalTypes();
    }
}
