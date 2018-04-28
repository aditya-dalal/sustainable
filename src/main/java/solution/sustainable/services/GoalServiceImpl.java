package solution.sustainable.services;

import com.google.inject.Inject;
import solution.sustainable.dao.GoalRepository;
import solution.sustainable.models.GoalTemplate;
import solution.sustainable.models.GoalType;

import java.util.List;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public class GoalServiceImpl implements GoalService {

    @Inject
    private GoalRepository goalRepository;

    @Override
    public GoalTemplate addGoalTemplate(GoalTemplate goalTemplate) {
        return goalRepository.insert(goalTemplate);
    }

    @Override
    public List<GoalTemplate> getGoalsForEnergyType(String energyType) {
        return goalRepository.findByEnergyType(energyType);
    }

    @Override
    public GoalType addGoalType(GoalType goalType) {
        return goalRepository.insert(goalType);
    }

    @Override
    public List<GoalType> getGoalTypes() {
        return goalRepository.findGoalTypes();
    }
}
