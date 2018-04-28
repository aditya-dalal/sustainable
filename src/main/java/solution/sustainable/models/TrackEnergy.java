package solution.sustainable.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by aditya.dalal on 28/04/18.
 */

@Entity(value = "trackEnergies", noClassnameStored = true)
@Setter
@Getter
public class TrackEnergy {
    @Id
    private ObjectId id;
    private String deviceId;

    public String getId() {
        return id.toString();
    }
}
