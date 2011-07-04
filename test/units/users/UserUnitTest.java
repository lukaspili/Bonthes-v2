package units.users;

import exceptions.CoreException;
import helpers.UserTestHelper;
import models.users.Profile;
import models.users.User;
import org.fest.assertions.Assertions;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import play.db.jpa.JPA;
import services.UserService;
import units.AbstractUnitTest;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class UserUnitTest extends AbstractUnitTest {

    private User simpleUser;
    private User savedUser;

    @Before
    public void load() {
        simpleUser = UserTestHelper.getSimpleUser();
        savedUser = UserTestHelper.getCompleteUser();
        savedUser.save();
    }

    @Test
    public void registerSuccess() {
        User wrapper = UserTestHelper.getUserForTest();
        User user = UserService.register(wrapper);
        compareBasicUsers(wrapper, user);
    }

    @Test
    public void registerStandardProfile() {
        User wrapper = UserTestHelper.getUserForTest();
        User user = UserService.register(wrapper);
        Profile profile = Profile.find("byName", Profile.CLIENT_STANDARD).first();
        Assertions.assertThat(user.profile).isEqualTo(profile);
    }

    @Test
    public void registerDate() {
        User wrapper = UserTestHelper.getUserForTest();
        User user = UserService.register(wrapper);
        Assertions.assertThat(user.registerDate.isAfter(new LocalDateTime().minusMinutes(1)))
                .as("register date is correct with -1min").isTrue();
        Assertions.assertThat(user.registerDate.isBefore(new LocalDateTime().plusMinutes(1)))
                .as("register date is correct with +1min").isTrue();
    }

    @Test
    public void registerHashPassword() {
        User wrapper = UserTestHelper.getUserForTest();
        User user = UserService.register(wrapper);
        comparePasswords(wrapper, user);
    }

    /**
     * Test the client identifier static pattern : CI + user id * 100 + regiser's month + register's year
     */
    @Test
    public void registerClientIdentifier() {
        User wrapper = UserTestHelper.getUserForTest();
        User user = UserService.register(wrapper);
        user.generateClientIdentifier();
        Assertions.assertThat(user.clientIdentifier).isEqualTo("CI" + (user.id * 100) +
                user.registerDate.monthOfYear().get() + user.registerDate.year().get());
    }

    @Test(expected = CoreException.class)
    public void registerMissingFieldsFailure() {
        User wrapper = UserTestHelper.getUserForTest();
        wrapper.email = null;
        UserService.register(wrapper);
    }

    @Test(expected = CoreException.class)
    public void registerExistingEmail() {
        UserService.register(simpleUser);
    }

    @Test
    public void loginSuccess() {
        User user = UserService.login(simpleUser.email, simpleUser.password);
        compareFullUsers(savedUser, user);
    }

    @Test(expected = CoreException.class)
    public void loginFailure() {
        UserService.login(simpleUser.email, simpleUser.password + "wrong");
    }

    @Test(expected = CoreException.class)
    public void loginBannedUserFailure() {
        UserService.ban(savedUser);
        UserService.login(simpleUser.email, simpleUser.password);
    }

    @Test
    public void updateSuccess() {

    }

    @Test
    public void updateFailure() {

    }

    @Test
    public void updateRestriction() {
        // check client identifier didn't changed
        // check register date didn't changed
        // check login date didn't changed
    }

    @Test
    public void userUpdateSuccess() {

        // detach before edit it because else the changes will be propagated to the hibernate 1st level cache
        JPA.em().detach(savedUser);
        savedUser.password = simpleUser.password + "wrong";

        UserService.userUpdate(savedUser);
        User user = User.findById(savedUser.id);

        compareFullUsers(savedUser, user);
        comparePasswords(savedUser, user);
    }

    @Test(expected = CoreException.class)
    public void userUpdateFailure() {

    }

    @Test
    public void userUpdateRestriction() {
        // check profile didn't changed

    }

    @Test
    public void adminUpdateSuccess() {

    }

    @Test(expected = CoreException.class)
    public void adminUpdateFailure() {

    }

    @Test
    public void adminUpdateRestriction() {
        // check password didn't changed
    }

    @Test
    public void banSuccess() {

    }

    @Test
    public void deleteInactiveUserSuccess() {

    }

    /**
     * Cannot delete user with shopping history
     */
    @Test(expected = CoreException.class)
    public void deleteActiveUserFailure() {

    }

    public void compareBasicUsers(User model, User user) {
        Assertions.assertThat(user.firstName).isEqualTo(model.firstName);
        Assertions.assertThat(user.lastName).isEqualTo(model.lastName);
        Assertions.assertThat(user.email).isEqualTo(model.email);
        Assertions.assertThat(user.street).isEqualTo(model.street);
        Assertions.assertThat(user.streetComplement).isEqualTo(model.streetComplement);
        Assertions.assertThat(user.city).isEqualTo(model.city);
        Assertions.assertThat(user.postalCode).isEqualTo(model.postalCode);
        Assertions.assertThat(user.country).isEqualTo(model.country);
        Assertions.assertThat(user.fax).isEqualTo(model.fax);
        Assertions.assertThat(user.phone).isEqualTo(model.phone);
        Assertions.assertThat(user.newsletter).isEqualTo(model.newsletter);
        Assertions.assertThat(user.socialType).isEqualTo(model.socialType);
    }

    public void compareFullUsers(User model, User user) {

        compareBasicUsers(model, user);

        Assertions.assertThat(user).isEqualTo(model);
        Assertions.assertThat(user.profile).isEqualTo(model.profile);
        Assertions.assertThat(user.clientIdentifier).isEqualTo(model.clientIdentifier);
        Assertions.assertThat(user.registerDate).isEqualTo(model.registerDate);

        // avoid the situation when user is registered but never logged
//        if (null != model.lastLoginDate) {
//            Assertions.assertThat(compare.lastLoginDate).isEqualTo(model.lastLoginDate);
//        }
    }

    public void comparePasswords(User model, User user) {
        Assertions.assertThat(new StrongPasswordEncryptor().checkPassword(model.password, user.password))
                .as("user password comparaison").isTrue();
    }
}
