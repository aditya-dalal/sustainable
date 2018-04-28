package solution.sustainable.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by aditya.dalal on 26/04/18.
 */

@Entity(value = "goals", noClassnameStored = true)
@Getter
@Setter
public class Goal {
    @Id
    private ObjectId id;
    private String deviceId;
    private String type;
    private String target;
    private String status;

    public String getId() {
        return id.toString();
    }
}
