package solution.sustainable.dao;

import solution.sustainable.models.Goal;
import solution.sustainable.models.GoalTemplate;
import solution.sustainable.models.GoalType;

import java.util.List;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public interface GoalRepository {
    GoalTemplate insert(GoalTemplate goalTemplate);
    GoalTemplate find(GoalTemplate goalTemplate);
    List<GoalTemplate> findByEnergyType(String energyType);
    List<Goal> findGoalsForDevice(String deviceId);
    GoalType insert(GoalType goalType);
    GoalType findGoalType(String goalType);
    List<GoalType> findGoalTypes();
}
