package solution.sustainable.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by aditya.dalal on 28/04/18.
 */

@Entity(value = "badges", noClassnameStored = true)
@Getter
@Setter
public class Badge {
    @Id
    private ObjectId id;
    private String name;

    public String getId() {
        return id.toString();
    }
}
