package helpers;

import models.users.Profile;
import models.users.SocialType;
import models.users.User;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.joda.time.LocalDateTime;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class UserTestHelper {

    /**
     * get the user that will be saved in database as fixture
     *
     * @return user
     */
    public static User getSimpleUser() {
        User user = new User();
        user.firstName = "Lukasz";
        user.lastName = "Piliszczuk";
        user.email = "lukasz.pili@gmail.com";
        user.password = "password123";
        user.street = "77 rue notre dame des champs";
        user.streetComplement = "Complement street";
        user.city = "Paris";
        user.postalCode = "75006";
        user.country = "FR";
        user.fax = "fax number";
        user.phone = "phone number";
        user.newsletter = true;
        user.socialType = SocialType.find("byName", SocialType.MR).first();

        return user;
    }

    public static User getCompleteUser() {

        User user = getSimpleUser();

        // auto generated fields
        user.password = new StrongPasswordEncryptor().encryptPassword(user.password);
        user.registerDate = new LocalDateTime();
        user.profile = Profile.find("byName", Profile.CLIENT_STANDARD).first();

        return user;
    }

    /**
     * get user saved in database as fixture
     *
     * @return user
     */
    public static User getUserFromDatabase() {
        User user = User.find("byEmail", getSimpleUser().email).first();
//        JPA.em().detach(user);
        return user;
    }

    /**
     * get a simple user for tests
     *
     * @return user
     */
    public static User getUserForTest() {
        User user = new User();
        user.firstName = "Clara";
        user.lastName = "Sos";
        user.email = "clara.sosp@gmail.com";
        user.password = "password987";
        user.street = "1 rue de longpont";
        user.city = "Longpont Sur Orge";
        user.postalCode = "91500";
        user.country = "FR";
        user.newsletter = false;
        user.socialType = SocialType.find("byName", SocialType.MLLE).first();

        return user;
    }
//    public static User registerUser() {
//        return registerUser(getUser());
//    }
//
//    public static User registerUser(User user) {
//        String password = USER_PASSWORD;
//        Long id = UserService.register(user.firstName, user.lastName, user.email, password, user.street,
//                user.streetComplement, user.city, user.postalCode, user.country, user.fax, user.phone,
//                user.newsletter, user.socialType).id;
//
//        return User.findById(id);
//    }
}
