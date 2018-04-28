package solution.sustainable.dao;

import org.bson.types.ObjectId;
import solution.sustainable.models.Energy;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public interface EnergyRepository {
    Energy find(Energy energy);
    Energy insert(Energy energy);
    Energy findById(ObjectId id);
    Energy findByName(String name);
}
