package solution.sustainable.services;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import solution.sustainable.dao.DeviceRepository;
import solution.sustainable.dao.EnergyRepository;
import solution.sustainable.dao.GoalRepository;
import solution.sustainable.dao.TrackEnergyRepository;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.*;

import java.util.List;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public class DeviceServiceImpl implements DeviceService {

    @Inject
    private DeviceRepository deviceRepository;
    @Inject
    private GoalRepository goalRepository;
    @Inject
    private EnergyRepository energyRepository;
    @Inject
    private TrackEnergyRepository trackEnergyRepository;

    @Override
    public Device register(Device device) throws InvalidRequestException {
        if(device.getId() == null)
            throw new InvalidRequestException(400, "Missing required field: id");
        Energy energy = energyRepository.findById(new ObjectId(device.getEnergyId()));
        if(energy == null)
            throw new InvalidRequestException(404, "Not found energyId: " + device.getEnergyId());
        return deviceRepository.insert(device);
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
        if(goal.getTarget() == null || goal.getType() == null)
            throw new InvalidRequestException(400, "Missing required field/s: deviceId|type|target");
        getDeviceById(deviceId);
        getGoalType(goal.getType());
        goal.setStatus("Active");
        goal.setDeviceId(deviceId);
        return deviceRepository.addGoalForDevice(deviceId, goal);
    }

    @Override
    public TrackEnergy addConsumption(String deviceId, Consumption consumption) throws InvalidRequestException {
        getDeviceById(deviceId);
        consumption.setType("consumption");
        consumption.setDeviceId(deviceId);
        return trackEnergyRepository.insert(consumption);
    }

    @Override
    public Device getDeviceById(String deviceId) throws InvalidRequestException {
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
