package solution.sustainable.dao;

import solution.sustainable.models.Owner;

/**
 * Created by aditya.dalal on 25/04/18.
 */
public interface OwnerRepository {
    Owner insert(Owner owner);
    Owner find(Owner owner);
    Owner findById(String id);
    boolean addDevice(String ownerId, String deviceId);
    boolean addBadge(String ownerId, String badgeId);
}
