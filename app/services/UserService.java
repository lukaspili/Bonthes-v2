package services;

import exceptions.CoreException;
import models.users.Profile;
import models.users.User;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.joda.time.LocalDateTime;
import play.Logger;
import utils.LocaleUtils;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class UserService {

    public static User get(Long id) {

        User user = User.findById(id);

        if (null == user) {
            throw new CoreException("User not found with id : " + id);
        }

        return user;
    }

    public static User getByEmail(String email) {
        try {
            User user = User.find("byEmail", email).first();
            if (null == user) {
                throw new RuntimeException();
            }

            return user;

        } catch (Exception e) {
            throw new CoreException("user not found with email : " + email, e);
        }
    }

    public static User register(User wrapper) {

        User user = new User();

        basicMapping(user, wrapper);

        // password hash
        user.password = getPasswordHash(wrapper.password);

        // profile
        try {
            user.profile = Profile.find("byName", Profile.CLIENT_STANDARD).first();
        } catch (Exception e) {
            throw new CoreException("profile client standard not found");
        }

        // automatic
        user.registerDate = new LocalDateTime();

        // client identifier is generated from @postpersist method
        user.clientIdentifier = "unkown";

        try {
            user = user.save();
        } catch (Exception e) {
            throw new CoreException("register exception", e);
        }

        return user;
    }

    public static User login(String email, String password) {

        User user = null;

        try {
            user = User.find("email=? and banned=?", email, false).first();
        } catch (Exception e) {
            throw new CoreException("login exception", e);
        }

        if (null == user) {
            throw new CoreException("user not found with email : " + email);
        }

        if (!new StrongPasswordEncryptor().checkPassword(password, user.password)) {
            throw new CoreException("user exists but invalid password with email : " + email);
        }

        // update last login date
        user.lastLoginDate = new LocalDateTime();
        user.save();

        return user;
    }

    private static void update(User user, User wrapper) {
        basicMapping(user, wrapper);
        user.save();
    }

    public static void userUpdate(User wrapper) {
        User user = get(wrapper.id);
        Logger.info("user pass from service : " + user.password);

        if (null != wrapper.password) {
            user.password = getPasswordHash(wrapper.password);
        }

        update(user, wrapper);
    }

    public static void adminUpdate(User wrapper) {
        User user = get(wrapper.id);
        user.profile = wrapper.profile;
        update(user, wrapper);
    }

    public static void ban(User user) {
        user.banned = true;
        user.save();
    }

    private static void basicMapping(User user, User wrapper) {
        user.firstName = wrapper.firstName;
        user.lastName = wrapper.lastName;
        user.email = wrapper.email;
        user.street = wrapper.street;
        user.streetComplement = wrapper.streetComplement;
        user.city = wrapper.city;
        user.postalCode = wrapper.postalCode;
        user.country = LocaleUtils.getValidCountryCode(wrapper.country);
        user.fax = wrapper.fax;
        user.phone = wrapper.phone;
        user.newsletter = wrapper.newsletter;
        user.socialType = wrapper.socialType;
    }

    /**
     * Externalized to make easy changes of hash algo
     *
     * @param password
     * @return hashed password
     */
    private static String getPasswordHash(String password) {
        return new StrongPasswordEncryptor().encryptPassword(password);
    }
}
