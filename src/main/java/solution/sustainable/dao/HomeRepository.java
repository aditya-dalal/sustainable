package solution.sustainable.dao;

import solution.sustainable.models.Home;

/**
 * Created by aditya.dalal on 25/04/18.
 */
public interface HomeRepository {
    Home insert(Home home);
    Home find(Home home);
    Home addDevice(String homeId, String deviceId);
}
