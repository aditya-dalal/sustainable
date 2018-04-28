package solution.sustainable.services;

import com.google.inject.Inject;
import solution.sustainable.dao.BadgeRepository;
import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Badge;

/**
 * Created by aditya.dalal on 28/04/18.
 */
public class BadgeServiceImpl implements BadgeService {

    @Inject
    private BadgeRepository badgeRepository;

    @Override
    public Badge add(Badge badge) throws InvalidRequestException {
        if(badgeRepository.find(badge) != null)
            throw new InvalidRequestException(409, "Badge already exists");
        return badgeRepository.insert(badge);
    }
}
