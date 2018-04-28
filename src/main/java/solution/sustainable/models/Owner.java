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

@Entity(value = "owners", noClassnameStored = true)
@Getter
@Setter
public class Owner {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private List<String> devices;
    private List<String> badges;

    public String getId() {
        return id.toString();
    }

}
