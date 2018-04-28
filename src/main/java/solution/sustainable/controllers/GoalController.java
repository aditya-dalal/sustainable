package solution.sustainable.controllers;

import com.google.inject.Inject;
import solution.sustainable.exceptions.Error;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Goal;
import solution.sustainable.models.GoalTemplate;
import solution.sustainable.models.GoalType;
import solution.sustainable.services.GoalService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by aditya.dalal on 26/04/18.
 */

@Path("/v1/goals")
public class GoalController {

    @Inject
    private GoalService goalService;

    @Path("/templates")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GoalTemplate addGoalTemplate(GoalTemplate goalTemplate) {
        return goalService.addGoalTemplate(goalTemplate);
    }

    @Path("/templates")
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

    @Path("/suggest")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response suggestGoals(@QueryParam("deviceId") String deviceId) {
        List<GoalTemplate> goalTemplates = null;
        try {
            goalTemplates = goalService.suggestGoals(deviceId);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(Error.newError(e)).build();
        }
        return Response.ok().entity(goalTemplates).build();
    }

    @Path("/goals")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGoal(Goal goal) {
        Goal result = null;
        try {
            validateGoal(goal);
            result = goalService.addGoal(goal.getDeviceId(), goal);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(Error.newError(e)).build();
        }
        return Response.ok().entity(result).build();
    }

    @Path("/goals")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGoals(@QueryParam("deviceId") String deviceId) {
        List<Goal> goals = null;
        try {
            goals = goalService.getGoals(deviceId);
        } catch (InvalidRequestException e) {
            return Response.status(e.getStatus()).entity(Error.newError(e)).build();
        }
        return Response.ok().entity(goals).build();
    }

    private void validateGoal(Goal goal) throws InvalidRequestException {
        if(goal.getTarget() == null || goal.getTarget().trim().equals(""))
            throw new InvalidRequestException(400, "Target cannot be null/empty");
        if(goal.getType() == null || goal.getType().trim().equals(""))
            throw new InvalidRequestException(400, "Type cannot be null/empty");
    }
}
