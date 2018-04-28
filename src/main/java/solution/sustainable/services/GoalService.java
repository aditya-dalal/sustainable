package solution.sustainable.services;

import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Goal;
import solution.sustainable.models.GoalTemplate;
import solution.sustainable.models.GoalType;

import java.util.List;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public interface GoalService {
    GoalTemplate addGoalTemplate(GoalTemplate goalTemplate);
    List<GoalTemplate> getGoalsForEnergyType(String energyType);
    GoalType addGoalType(GoalType goalType);
    List<GoalType> getGoalTypes();
    List<GoalTemplate> suggestGoals(String deviceId) throws InvalidRequestException;
    List<Goal> getGoals(String deviceId) throws InvalidRequestException;
    Goal addGoal(String deviceId, Goal goal) throws InvalidRequestException;
}
