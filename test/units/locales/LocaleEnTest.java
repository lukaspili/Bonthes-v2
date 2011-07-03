package units.locales;

import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class LocaleEnTest extends UnitTest {

    @Before
    public void before() {
        // En locale is disabled for now
//        Lang.set("en");
    }

    @Test
    public void countries() {
//        Assertions.assertThat(UserExtensions.localeCountry("GB")).isEqualTo("United Kingdom");
    }
}
