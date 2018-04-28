package solution.sustainable.dao;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import solution.sustainable.models.Energy;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public class EnergyRepositoryImpl implements EnergyRepository {

    @Inject
    private Datastore datastore;

    @Override
    public Energy find(Energy energy) {
        return datastore.find(Energy.class).field("name").equal(energy.getName())
                .field("type").equal(energy.getType()).get();
    }

    @Override
    public Energy insert(Energy energy) {
        datastore.save(energy);
        return energy;
    }

    @Override
    public Energy findById(ObjectId id) {
        return datastore.find(Energy.class).field("_id").equal(id).get();
    }
}
