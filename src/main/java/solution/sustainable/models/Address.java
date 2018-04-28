package solution.sustainable.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by aditya.dalal on 25/04/18.
 */

@Getter
@Setter
public class Address {
    private String houseNumber;
    private String street;
    private String country;
    private Integer zipcode;
}
