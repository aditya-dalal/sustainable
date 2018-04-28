package solution.sustainable.services;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import solution.sustainable.dao.DeviceRepository;
import solution.sustainable.dao.EnergyRepository;
import solution.sustainable.dao.GoalRepository;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.*;

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

    @Override
    public List<GoalTemplate> suggestGoals(String deviceId) throws InvalidRequestException {
        Energy energy = energyRepository.findById(new ObjectId(getDeviceById(deviceId).getEnergyId()));
        System.out.println(energy.getId());
        return goalRepository.findByEnergyType(energy.getType());
    }

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
        return deviceRepository.addGoalForDevice(deviceId, goal);
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
}
