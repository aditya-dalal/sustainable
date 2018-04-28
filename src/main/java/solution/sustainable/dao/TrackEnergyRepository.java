package solution.sustainable.dao;

import solution.sustainable.models.TrackEnergy;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public interface TrackEnergyRepository {
    TrackEnergy insert(TrackEnergy consumption);
}
