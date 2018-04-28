package solution.sustainable.dao;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import solution.sustainable.models.Energy;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public class EnergyRepositoryImpl implements EnergyRepository {

    @Inject
    private Datastore datastore;

    @Override
    public Energy find(Energy energy) {
        Query<Energy> query = datastore.createQuery(Energy.class).field("name").equal(energy.getName());
        return query.get();
    }

    @Override
    public Energy insert(Energy energy) {
        Energy dbEnergy = find(energy);
        if(dbEnergy == null) {
            datastore.save(energy);
            return energy;
        }
        return dbEnergy;
    }

    @Override
    public Energy findById(ObjectId id) {
        return datastore.find(Energy.class).field("_id").equal(id).get();
    }
}
