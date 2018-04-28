package solution.sustainable.services;

import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.*;

import java.util.List;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public interface DeviceService {
    Device register(Device device) throws InvalidRequestException;
    List<GoalTemplate> suggestGoals(String deviceId) throws InvalidRequestException;
    List<Goal> getGoals(String deviceId) throws InvalidRequestException;
    Goal addGoal(String deviceId, Goal goal) throws InvalidRequestException;
    TrackEnergy addConsumption(String deviceId, Consumption consumption) throws InvalidRequestException;
    Device getDeviceById(String deviceId) throws InvalidRequestException;
}
