package units.users;

import helpers.UserTestHelper;
import models.users.User;
import org.fest.assertions.Assertions;
import org.junit.Test;
import units.AbstractUnitTest;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class UserUtilsTest extends AbstractUnitTest {

    /**
     * Test the client identifier static pattern : CI + user id * 100 + regiser's month + register's year
     */
//    @Test
//    public void clientIdentifierPattern() {
//        User user = User.find("byEmail", UserTestHelper.getUserFromModel().email).first();
//        String identifier = "CI" + (user.id * 100) + user.registerDate.monthOfYear().get() + user.registerDate.year().get();
//        Assertions.assertThat(user.clientIdentifier).isEqualTo(identifier);
//    }
}
