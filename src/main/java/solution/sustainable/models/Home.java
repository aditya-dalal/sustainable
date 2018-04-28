package solution.sustainable.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * Created by aditya.dalal on 25/04/18.
 */

@Entity(value = "homes", noClassnameStored = true)
@Getter
@Setter
public class Home {
    @Id
    private ObjectId id;
    private Owner owner;
    private Address address;
    private List<String> devices;

    public String getId() {
        return id.toString();
    }
}
