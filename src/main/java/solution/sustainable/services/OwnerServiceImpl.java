package solution.sustainable.services;

import com.google.inject.Inject;
import solution.sustainable.dao.BadgeRepository;
import solution.sustainable.dao.DeviceRepository;
import solution.sustainable.dao.OwnerRepository;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Owner;

/**
 * Created by aditya.dalal on 28/04/18.
 */
public class OwnerServiceImpl implements OwnerService {
    @Inject
    private OwnerRepository ownerRepository;
    @Inject
    private DeviceRepository deviceRepository;
    @Inject
    private BadgeRepository badgeRepository;

    @Override
    public Owner register(Owner owner) throws InvalidRequestException {
        if(ownerRepository.find(owner) != null)
            throw new InvalidRequestException(409, "Owner already exists");
        return ownerRepository.insert(owner);
    }

    @Override
    public boolean addDevice(String ownerId, String deviceId) throws InvalidRequestException {
        if(ownerRepository.findById(ownerId) == null)
            throw new InvalidRequestException(400, "Owner does not exist: " + ownerId);
        if(deviceRepository.findById(deviceId) == null)
            throw new InvalidRequestException(400, "Device does not exist: " + deviceId);
        return ownerRepository.addDevice(ownerId, deviceId);
    }

    @Override
    public boolean addBadge(String ownerId, String badgeId) throws InvalidRequestException {
        if(ownerRepository.findById(ownerId) == null)
            throw new InvalidRequestException(400, "Owner does not exist: " + ownerId);
        if(badgeRepository.findById(badgeId) == null)
            throw new InvalidRequestException(400, "Badge does not exist: " + badgeId);
        return ownerRepository.addBadge(ownerId, badgeId);
    }
}
