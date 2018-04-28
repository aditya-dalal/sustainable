package solution.sustainable.dao;

import com.google.inject.Inject;
import org.mongodb.morphia.Datastore;
import solution.sustainable.models.Consumption;
import solution.sustainable.models.TrackEnergy;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public class TrackEnergyRepositoryImpl implements TrackEnergyRepository {

    @Inject
    private Datastore datastore;

    @Override
    public TrackEnergy insert(TrackEnergy trackEnergy) {
        datastore.save(trackEnergy);
        return trackEnergy;
    }
}
