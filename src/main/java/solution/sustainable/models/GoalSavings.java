package solution.sustainable.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by aditya.dalal on 28/04/18.
 */

@Getter
@Setter
public class GoalSavings {
    private String goalType;
    private String target;
    private String energyType;
    private Double savingsPerUnit;
}
