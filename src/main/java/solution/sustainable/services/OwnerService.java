package solution.sustainable.services;

import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Owner;

/**
 * Created by aditya.dalal on 28/04/18.
 */
public interface OwnerService {
    Owner register(Owner owner) throws InvalidRequestException;
    boolean addDevice(String ownerId, String deviceId) throws InvalidRequestException;
    boolean addBadge(String ownerId, String badgeId) throws InvalidRequestException;
}
