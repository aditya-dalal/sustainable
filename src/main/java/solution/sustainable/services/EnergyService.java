package solution.sustainable.services;

import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Energy;

/**
 * Created by aditya.dalal on 28/04/18.
 */
public interface EnergyService {
    Energy add(Energy energy) throws InvalidRequestException;
}
