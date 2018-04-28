package solution.sustainable.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by aditya.dalal on 25/04/18.
 */

@Entity(value = "energies", noClassnameStored = true)
@Getter
@Setter
public class Energy {
    @Id
    private ObjectId id;
    private String name;
    private String type;
    private String unit;
    private Double cost;

    public String getId() {
        return id.toString();
    }
}
