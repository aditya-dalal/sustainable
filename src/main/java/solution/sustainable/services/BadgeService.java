package solution.sustainable.services;

import solution.sustainable.exceptions.InvalidRequestException;
import solution.sustainable.models.Badge;

/**
 * Created by aditya.dalal on 28/04/18.
 */
public interface BadgeService {
    Badge add(Badge badge) throws InvalidRequestException;
}
