package solution.sustainable;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import solution.sustainable.config.SustainableConfiguration;
import solution.sustainable.dao.*;
import solution.sustainable.services.*;

/**
 * Created by aditya.dalal on 25/04/18.
 */
public class SustainableModule extends AbstractModule {

    @Override
    public void configure() {
        bind(HomeRepository.class).to(HomeRepositoryImpl.class);
        bind(DeviceRepository.class).to(DeviceRepositoryImpl.class);
        bind(EnergyRepository.class).to(EnergyRepositoryImpl.class);
        bind(TrackEnergyRepository.class).to(TrackEnergyRepositoryImpl.class);
        bind(GoalRepository.class).to(GoalRepositoryImpl.class);
        bind(DeviceService.class).to(DeviceServiceImpl.class);
        bind(GoalService.class).to(GoalServiceImpl.class);
        bind(EnergyService.class).to(EnergyServiceImpl.class);
    }

    @Singleton
    @Provides
    public Datastore datastore(SustainableConfiguration configuration) {
        MongoClientURI connectionString = new MongoClientURI("mongodb://" + configuration.getMongo().getServers());
        MongoClient mongoClient = new MongoClient(connectionString);
        final Morphia morphia = new Morphia();
        morphia.mapPackage("solution.sustainable.models");
        final Datastore datastore = morphia.createDatastore(mongoClient, configuration.getMongo().getDatabase());
        datastore.ensureIndexes();
        return datastore;
    }
}
