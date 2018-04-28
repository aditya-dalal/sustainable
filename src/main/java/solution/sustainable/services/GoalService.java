package solution.sustainable.services;

import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Goal;
import solution.sustainable.models.GoalSavings;
import solution.sustainable.models.GoalTemplate;
import solution.sustainable.models.GoalType;

import java.util.List;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public interface GoalService {
    List<Goal> getGoals(String deviceId) throws InvalidRequestException;
    Goal addGoal(String deviceId, Goal goal) throws InvalidRequestException;
    List<GoalSavings> suggestGoals(String deviceId) throws InvalidRequestException;
    GoalTemplate addGoalTemplate(GoalTemplate goalTemplate) throws InvalidRequestException;
    List<GoalTemplate> getGoalsForEnergyType(String energyType);
    GoalType addGoalType(GoalType goalType) throws InvalidRequestException;
    List<GoalType> getGoalTypes();
}
