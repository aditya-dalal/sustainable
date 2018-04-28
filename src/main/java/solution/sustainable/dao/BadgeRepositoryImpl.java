package solution.sustainable.dao;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import solution.sustainable.models.Badge;

/**
 * Created by aditya.dalal on 28/04/18.
 */
public class BadgeRepositoryImpl implements BadgeRepository {

    @Inject
    private Datastore datastore;

    @Override
    public Badge insert(Badge badge) {
        datastore.save(badge);
        return badge;
    }

    @Override
    public Badge find(Badge badge) {
        return datastore.find(Badge.class).field("name").equal(badge.getName()).get();
    }

    @Override
    public Badge findById(String id) {
        return datastore.find(Badge.class).field("_id").equal(new ObjectId(id)).get();
    }
}
