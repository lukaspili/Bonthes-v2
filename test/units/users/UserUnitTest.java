package units.users;

import exceptions.CoreException;
import helpers.ModelTestHelper;
import models.users.Profile;
import models.users.User;
import org.fest.assertions.Assertions;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import services.UserService;
import units.AbstractUnitTest;
import utils.UserUtils;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class UserUnitTest extends AbstractUnitTest {

    @Test
    public void registerSuccess() {
        User user = ModelTestHelper.getUser();
        User res = ModelTestHelper.registerUser(user);
        compareBasicUsers(user, res);
    }

    @Test
    public void registerStandardProfile() {
        User user = ModelTestHelper.registerUser();
        Profile profile = Profile.find("byName", Profile.CLIENT_STANDARD).first();
        Assertions.assertThat(user.profile).isEqualTo(profile);
    }

    @Test
    public void registerDate() {
        User user = ModelTestHelper.registerUser();
        Assertions.assertThat(user.registerDate.isAfter(new LocalDateTime().minusMinutes(1)))
                .as("register date is correct with -1min").isTrue();
        Assertions.assertThat(user.registerDate.isBefore(new LocalDateTime().plusMinutes(1)))
                .as("register date is correct with +1min").isTrue();
    }

    @Test
    public void registerHashPassword() {
        User user = ModelTestHelper.registerUser();

        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        String hash = encryptor.encryptPassword(ModelTestHelper.USER_PASSWORD);
        Assertions.assertThat(encryptor.checkPassword(ModelTestHelper.USER_PASSWORD, user.passwordHash)).isTrue();
        Assertions.assertThat(encryptor.checkPassword(ModelTestHelper.USER_PASSWORD, hash)).isTrue();
    }

    @Test
    public void registerClientIdentifier() {
        User user = ModelTestHelper.registerUser();
        String identifier = UserUtils.getClientIdentifierForUser(user);
        Assertions.assertThat(user.clientIdentifier).isEqualTo(identifier);
    }

    @Test(expected = CoreException.class)
    public void registerFailure() {
        User user = ModelTestHelper.getUser();
        user.email = null;
        ModelTestHelper.registerUser(user);
    }

    @Test(expected = CoreException.class)
    public void registerExistingEmail() {
        User user = ModelTestHelper.registerUser();
        ModelTestHelper.registerUser(user);
    }

    @Test
    public void loginSuccess() {
        User user = ModelTestHelper.registerUser();
        User logged = UserService.login(user.email, ModelTestHelper.USER_PASSWORD);
        compareFullUsers(user, logged);
    }

    @Test(expected = CoreException.class)
    public void loginFailure() {
        User user = ModelTestHelper.registerUser();
        UserService.login(user.email, "wrong password");
    }

    @Test(expected = CoreException.class)
    public void loginBannedUserFailure() {
        User user = ModelTestHelper.registerUser();
        UserService.ban(user);
        UserService.login(user.email, ModelTestHelper.USER_PASSWORD);
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

    public void compareBasicUsers(User model, User compare) {
        Assertions.assertThat(compare.firstName).isEqualTo(model.firstName);
        Assertions.assertThat(compare.lastName).isEqualTo(model.lastName);
        Assertions.assertThat(compare.email).isEqualTo(model.email);
        Assertions.assertThat(compare.street).isEqualTo(model.street);
        Assertions.assertThat(compare.streetComplement).isEqualTo(model.streetComplement);
        Assertions.assertThat(compare.city).isEqualTo(model.city);
        Assertions.assertThat(compare.postalCode).isEqualTo(model.postalCode);
        Assertions.assertThat(compare.country).isEqualTo(model.country);
        Assertions.assertThat(compare.fax).isEqualTo(model.fax);
        Assertions.assertThat(compare.phone).isEqualTo(model.phone);
        Assertions.assertThat(compare.newsletter).isEqualTo(model.newsletter);
        Assertions.assertThat(compare.socialType).isEqualTo(model.socialType);
    }

    public void compareFullUsers(User model, User compare) {

        compareBasicUsers(model, compare);

        Assertions.assertThat(compare).isEqualTo(model);
        Assertions.assertThat(compare.profile).isEqualTo(model.profile);
        Assertions.assertThat(compare.clientIdentifier).isEqualTo(model.clientIdentifier);
        Assertions.assertThat(compare.registerDate).isEqualTo(model.registerDate);

        // avoid the situation when user is registered but never logged
        if (null != model.lastLoginDate) {
            Assertions.assertThat(compare.lastLoginDate).isEqualTo(model.lastLoginDate);
        }
    }
}
