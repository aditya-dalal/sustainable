package solution.sustainable.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by aditya.dalal on 25/04/18.
 */

@Getter
@Setter
public class Owner {
    private String firstName;
    private String lastName;
    private List<String> badges;
}
