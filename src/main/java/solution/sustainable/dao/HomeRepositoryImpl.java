package solution.sustainable.dao;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import solution.sustainable.models.Home;

/**
 * Created by aditya.dalal on 25/04/18.
 */
public class HomeRepositoryImpl implements HomeRepository {

    private Datastore datastore;

    @Inject
    public HomeRepositoryImpl(Datastore datastore) {
        this.datastore = datastore;
    }

    private static final String HOMES_COLLECTION = "homes";

    @Override
    public Home insert(Home home) {
        Home result = find(home);
        if(result == null) {
            datastore.save(home);
            return home;
        }
        return result;
    }

    @Override
    public Home find(Home home) {
        Query<Home> query = datastore.createQuery(Home.class);
        query.filter("address.houseNumber", home.getAddress().getHouseNumber())
                .filter("address.zipcode", home.getAddress().getZipcode())
                .filter("address.country", home.getAddress().getCountry());
        return query.get();
    }

    @Override
    public Home addDevice(String homeId, String deviceId) {
        Query<Home> query = datastore.createQuery(Home.class).field("_id").equal(new ObjectId(homeId));
        UpdateOperations<Home> ops = datastore.createUpdateOperations(Home.class).addToSet("devices", deviceId);
        return datastore.findAndModify(query, ops);
    }

}
