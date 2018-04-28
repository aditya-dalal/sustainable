package solution.sustainable.config;

import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by aditya.dalal on 25/04/18.
 */

@Getter
@Setter
public class SustainableConfiguration extends Configuration {
    private MongoConfiguration mongo;
}
