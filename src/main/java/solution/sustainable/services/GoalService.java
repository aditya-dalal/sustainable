package solution.sustainable.services;

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
}
