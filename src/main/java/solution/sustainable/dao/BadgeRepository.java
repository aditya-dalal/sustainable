package solution.sustainable.dao;

import solution.sustainable.models.Badge;

/**
 * Created by aditya.dalal on 28/04/18.
 */
public interface BadgeRepository {
    Badge insert(Badge badge);
    Badge find(Badge badge);
    Badge findById(String badgeId);
}
