package solution.sustainable.services;

import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Consumption;
import solution.sustainable.models.Device;
import solution.sustainable.models.TrackEnergy;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public interface DeviceService {
    Device register(Device device) throws InvalidRequestException;
    TrackEnergy addConsumption(String deviceId, Consumption consumption) throws InvalidRequestException;
}
