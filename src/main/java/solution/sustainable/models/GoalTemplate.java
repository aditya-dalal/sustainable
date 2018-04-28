package solution.sustainable.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by aditya.dalal on 25/04/18.
 */

@Entity(value = "goalTemplates", noClassnameStored = true)
@Getter
@Setter
public class GoalTemplate {
    @Id
    private ObjectId id;
    private String type;
    private String target;
    private String energyType;

    public String getId() {
        return id.toString();
    }
}
