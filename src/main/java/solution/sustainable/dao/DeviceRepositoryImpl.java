package solution.sustainable.dao;

import com.google.inject.Inject;
import org.mongodb.morphia.Datastore;
import solution.sustainable.models.Device;
import solution.sustainable.models.Goal;

/**
 * Created by aditya.dalal on 25/04/18.
 */
public class DeviceRepositoryImpl implements DeviceRepository {

    @Inject
    private Datastore datastore;

    @Override
    public Device insert(Device device) {
        datastore.save(device);
        return device;
    }

    @Override
    public Device findById(String id) {
        return datastore.find(Device.class).field("_id").equal(id).get();
    }

}
