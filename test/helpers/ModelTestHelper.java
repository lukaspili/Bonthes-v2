package helpers;

import models.users.SocialType;
import models.users.User;
import services.UserService;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class ModelTestHelper {

    public static final String USER_PASSWORD = "password123";

    public static User getUser() {
        User user = new User();
        user.firstName = "Lukasz";
        user.lastName = "Piliszczuk";
        user.email = "lukasz.pili@gmail.com";
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

    public static User registerUser() {
        return registerUser(getUser());
    }

    public static User registerUser(User user) {
        String password = USER_PASSWORD;
        Long id = UserService.register(user.firstName, user.lastName, user.email, password, user.street,
                user.streetComplement, user.city, user.postalCode, user.country, user.fax, user.phone,
                user.newsletter, user.socialType).id;

        return User.findById(id);
    }
}
