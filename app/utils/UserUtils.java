package utils;

import models.users.User;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class UserUtils {

    public static String getClientIdentifierForUser(User user) {
        return "CI" + (user.id * 100) + user.registerDate.monthOfYear().get() + user.registerDate.year()
                .get();
    }
}
