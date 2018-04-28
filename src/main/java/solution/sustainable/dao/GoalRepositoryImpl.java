package solution.sustainable.dao;

import com.google.inject.Inject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import solution.sustainable.models.Goal;
import solution.sustainable.models.GoalTemplate;
import solution.sustainable.models.GoalType;

import java.util.List;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public class GoalRepositoryImpl implements GoalRepository {

    @Inject
    private Datastore datastore;

    @Override
    public Goal insert(Goal goal) {
        datastore.save(goal);
        return goal;
    }

    @Override
    public Goal find(Goal goal) {
        return datastore.find(Goal.class).field("deviceId").equal(goal.getDeviceId()).field("type").equal(goal.getType())
                .field("target").equal(goal.getTarget()).field("status").notEqual(goal.getStatus()).get();
    }

    @Override
    public GoalTemplate insert(GoalTemplate goalTemplate) {
        GoalTemplate dbGoalTemplate = find(goalTemplate);
        if(dbGoalTemplate == null) {
            datastore.save(goalTemplate);
            return goalTemplate;
        }
        return dbGoalTemplate;
    }

    @Override
    public GoalTemplate find(GoalTemplate goalTemplate) {
        Query<GoalTemplate> query = datastore.createQuery(GoalTemplate.class).field("type").equal(goalTemplate.getType())
                .field("target").equal(goalTemplate.getTarget()).field("energyType").equal(goalTemplate.getEnergyType());
        return query.get();
    }

    @Override
    public List<GoalTemplate> findByEnergyType(String energyType) {
        Query<GoalTemplate> query = datastore.createQuery(GoalTemplate.class).field("energyType").equal(energyType);
        return query.asList();
    }

    @Override
    public List<Goal> findGoalsForDevice(String deviceId) {
        return datastore.find(Goal.class).field("deviceId").equal(deviceId).asList();
    }

    @Override
    public GoalType insert(GoalType goalType) {
        datastore.save(goalType);
        return goalType;
    }

    @Override
    public GoalType find(GoalType goalType) {
        return datastore.find(GoalType.class).field("type").equal(goalType.getType()).get();
    }

    @Override
    public GoalType findGoalType(String goalType) {
        return datastore.find(GoalType.class).field("type").equal(goalType).get();
    }

    @Override
    public List<GoalType> findGoalTypes() {
        return datastore.find(GoalType.class).asList();
    }


}
