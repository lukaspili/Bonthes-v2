package services;

import exceptions.CoreException;
import models.users.Profile;
import models.users.SocialType;
import models.users.User;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.joda.time.LocalDateTime;
import play.Logger;
import utils.LocaleUtils;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class UserService {

    public static User register(String firstName, String lastName, String email, String password,
                                String street, String streetComplement, String city,
                                String postalCode, String country,
                                String fax, String phone, Boolean newsletter, SocialType socialType) {

        User user = new User();

        // mapping from controller
        user.firstName = firstName;
        user.lastName = lastName;
        user.email = email;
        user.street = street;
        user.streetComplement = streetComplement;
        user.city = city;
        user.postalCode = postalCode;
        user.fax = fax;
        user.phone = phone;
        user.newsletter = newsletter;
        user.socialType = socialType;

        // check country validity
        user.country = LocaleUtils.getValidCountryCode(country);

        if (null == user.country || user.country.isEmpty()) {
            throw new CoreException("locale is invalid for country : " + country);
        }

        // password hash
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        user.passwordHash = encryptor.encryptPassword(password);

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
            user = User.find("byEmail and banned", email, false).first();
        } catch (Exception e) {
            throw new CoreException("login exception", e);
        }

        if (null == user) {
            throw new CoreException("user not found with email : " + email);
        }

        Logger.info("pass = " + user.passwordHash);

        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

        if (!encryptor.checkPassword(password, user.passwordHash)) {
            throw new CoreException("user exists but invalid password with email : " + email);
        }

        return user;
    }

    public static void ban(User user) {
        user.banned = true;
        user.save();
    }
}
