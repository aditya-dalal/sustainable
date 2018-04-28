package solution.sustainable;

import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import solution.sustainable.config.SustainableConfiguration;

public class SustainableApplication extends Application<SustainableConfiguration> {

    private GuiceBundle<SustainableConfiguration> guiceBundle;

    @Override
    public void initialize(Bootstrap<SustainableConfiguration> bootstrap) {
        guiceBundle = GuiceBundle.<SustainableConfiguration>newBuilder()
                .addModule(new SustainableModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(SustainableConfiguration.class)
                .build(Stage.DEVELOPMENT);
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(SustainableConfiguration configuration, Environment environment) {}

    public static void main(String[] args) throws Exception {
        new SustainableApplication().run(args);
    }
}
