package solution.sustainable.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by aditya.dalal on 25/04/18.
 */

@Entity(value = "devices", noClassnameStored = true)
@Getter
@Setter
public class Device {
    @Id
    private String id;
    private String name;
    private ObjectId energyId;

    public String getEnergyId() {
        return energyId.toString();
    }
}
