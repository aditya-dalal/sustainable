package solution.sustainable.services;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import solution.sustainable.dao.DeviceRepository;
import solution.sustainable.dao.EnergyRepository;
import solution.sustainable.dao.TrackEnergyRepository;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Consumption;
import solution.sustainable.models.Device;
import solution.sustainable.models.TrackEnergy;


/**
 * Created by aditya.dalal on 26/04/18.
 */
public class DeviceServiceImpl implements DeviceService {

    @Inject
    private DeviceRepository deviceRepository;
    @Inject
    private EnergyRepository energyRepository;
    @Inject
    private TrackEnergyRepository trackEnergyRepository;

    @Override
    public Device register(Device device) throws InvalidRequestException {
        if(deviceRepository.findById(device.getId()) != null)
            throw new InvalidRequestException(400, "DeviceId already exists: " + device.getId());
        if(energyRepository.findById(new ObjectId(device.getEnergyId())) == null)
            throw new InvalidRequestException(404, "Not found energyId: " + device.getEnergyId());
        return deviceRepository.insert(device);
    }

    @Override
    public TrackEnergy addConsumption(String deviceId, Consumption consumption) throws InvalidRequestException {
        getDeviceById(deviceId);
        consumption.setType("consumption");
        consumption.setDeviceId(deviceId);
        return trackEnergyRepository.insert(consumption);
    }

    private Device getDeviceById(String deviceId) throws InvalidRequestException {
        Device device = deviceRepository.findById(deviceId);
        if(device == null)
            throw new InvalidRequestException(404, "Not found deviceId: " + deviceId);
        return device;
    }

}
