package solution.sustainable.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by aditya.dalal on 26/04/18.
 */

@Entity(value = "goalTypes", noClassnameStored = true)
@Getter
@Setter
public class GoalType {
    @Id
    private ObjectId id;
    private String type;

    public String getId() {
        return id.toString();
    }
}
