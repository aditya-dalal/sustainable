package solution.sustainable.dao;

import solution.sustainable.models.Device;
import solution.sustainable.models.Goal;

/**
 * Created by aditya.dalal on 25/04/18.
 */
public interface DeviceRepository {
    Device insert(Device device);
    Device findById(String id);
    Goal addGoalForDevice(String id, Goal goal);
}
