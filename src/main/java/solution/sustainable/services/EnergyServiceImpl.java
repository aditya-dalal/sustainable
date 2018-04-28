package solution.sustainable.services;

import com.google.inject.Inject;
import solution.sustainable.dao.EnergyRepository;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Energy;

/**
 * Created by aditya.dalal on 28/04/18.
 */
public class EnergyServiceImpl implements EnergyService {

    @Inject
    private EnergyRepository energyRepository;

    @Override
    public Energy add(Energy energy) throws InvalidRequestException {
        if(energyRepository.find(energy) != null)
            throw new InvalidRequestException(409, "Energy already exists");
        return energyRepository.insert(energy);
    }
}
