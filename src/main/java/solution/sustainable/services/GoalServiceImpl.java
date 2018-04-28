package solution.sustainable.services;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import solution.sustainable.dao.DeviceRepository;
import solution.sustainable.dao.EnergyRepository;
import solution.sustainable.dao.GoalRepository;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public class GoalServiceImpl implements GoalService {

    @Inject
    private GoalRepository goalRepository;
    @Inject
    private EnergyRepository energyRepository;
    @Inject
    private DeviceRepository deviceRepository;

    @Override
    public List<Goal> getGoals(String deviceId) throws InvalidRequestException {
        return goalRepository.findGoalsForDevice(deviceId);
    }

    @Override
    public Goal addGoal(String deviceId, Goal goal) throws InvalidRequestException {
        getDeviceById(deviceId);
        getGoalType(goal.getType());
        goal.setStatus("Active");
        goal.setDeviceId(deviceId);
        if(goalRepository.find(goal) != null)
            throw new InvalidRequestException(409, "Goal already exists");
        return goalRepository.insert(goal);
    }

    @Override
    public List<GoalSavings> suggestGoals(String deviceId) throws InvalidRequestException {
        Device device = getDeviceById(deviceId);
        Energy energy = energyRepository.findById(new ObjectId(device.getEnergyId()));
        List<GoalTemplate> goals = goalRepository.findByEnergyType(energy.getType());
        return getGoalsWithSavings(goals, energy);
    }

    @Override
    public GoalTemplate addGoalTemplate(GoalTemplate goalTemplate) throws InvalidRequestException {
        if(goalRepository.find(goalTemplate) != null)
            throw new InvalidRequestException(409, "Goal template already exists");
        return goalRepository.insert(goalTemplate);
    }

    @Override
    public List<GoalTemplate> getGoalsForEnergyType(String energyType) {
        return goalRepository.findByEnergyType(energyType);
    }

    @Override
    public GoalType addGoalType(GoalType goalType) throws InvalidRequestException {
        if(goalRepository.find(goalType) != null)
            throw new InvalidRequestException(409, "GoalType already exists");
        return goalRepository.insert(goalType);
    }

    @Override
    public List<GoalType> getGoalTypes() {
        return goalRepository.findGoalTypes();
    }

    private Device getDeviceById(String deviceId) throws InvalidRequestException {
        Device device = deviceRepository.findById(deviceId);
        if(device == null)
            throw new InvalidRequestException(404, "Not found deviceId: " + deviceId);
        return device;
    }

    private GoalType getGoalType(String goalType) throws InvalidRequestException {
        GoalType result = goalRepository.findGoalType(goalType);
        if(result == null)
            throw new InvalidRequestException(404, "Not found goal type: " + goalType);
        return result;
    }

    private List<GoalSavings> getGoalsWithSavings(List<GoalTemplate> goals, Energy energy) {
        List<GoalSavings> result = new ArrayList<>();
        for (GoalTemplate goal: goals) {
            if(goal.getType().equals("Use Alternate")) {
                Energy goalEnergy = energyRepository.findByName(goal.getTarget());
                result.add(goalSavings(goal, energy.getCost() - goalEnergy.getCost()));
            }
            else if(goal.getType().equals("Reduce By")) {
                Double savings = energy.getCost() * (Double.parseDouble(goal.getTarget())/100);
                result.add(goalSavings(goal, savings));
            }
            else if(goal.getType().equals("Switch To")) {
                result.add(goalSavings(goal, 0.0));
            }
        }
        return result;
    }

    private GoalSavings goalSavings(GoalTemplate goal, Double saving) {
        GoalSavings goalSavings = new GoalSavings();
        goalSavings.setEnergyType(goal.getEnergyType());
        goalSavings.setSavingsPerUnit(saving);
        goalSavings.setTarget(goal.getTarget());
        goalSavings.setGoalType(goal.getType());
        return goalSavings;
    }
}
