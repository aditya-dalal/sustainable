package solution.sustainable.dao;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import solution.sustainable.models.Owner;

/**
 * Created by aditya.dalal on 25/04/18.
 */
public class OwnerRepositoryImpl implements OwnerRepository {

    @Inject
    private Datastore datastore;

    @Override
    public Owner insert(Owner owner) {
        datastore.save(owner);
        return owner;
    }

    @Override
    public Owner find(Owner owner) {
        return datastore.find(Owner.class).field("firstName").equal(owner.getFirstName())
                .field("lastName").equal(owner.getLastName()).get();
    }

    @Override
    public Owner findById(String id) {
        return datastore.find(Owner.class).field("_id").equal(new ObjectId(id)).get();
    }

    @Override
    public boolean addDevice(String ownerId, String deviceId) {
        Query<Owner> query = datastore.createQuery(Owner.class).field("_id").equal(new ObjectId(ownerId));
        UpdateOperations<Owner> ops = datastore.createUpdateOperations(Owner.class).addToSet("devices", deviceId);
        UpdateResults result = datastore.update(query, ops);
        if(result.getUpdatedCount() != 1)
            return false;
        return true;
    }

    @Override
    public boolean addBadge(String ownerId, String badgeId) {
        Query<Owner> query = datastore.createQuery(Owner.class).field("_id").equal(new ObjectId(ownerId));
        UpdateOperations<Owner> ops = datastore.createUpdateOperations(Owner.class).addToSet("badges", badgeId);
        UpdateResults result = datastore.update(query, ops);
        if(result.getUpdatedCount() != 1)
            return false;
        return true;
    }

}
