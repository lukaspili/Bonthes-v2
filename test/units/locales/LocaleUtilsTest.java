package units.locales;

import exceptions.CoreException;
import org.fest.assertions.Assertions;
import org.junit.Test;
import play.test.UnitTest;
import utils.LocaleUtils;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class LocaleUtilsTest extends UnitTest {

    @Test
    public void countryCodeValid() {
        String country = LocaleUtils.getValidCountryCode("FR");
        Assertions.assertThat(country).isEqualTo("FR");
    }

    @Test(expected = CoreException.class)
    public void countryCodeInvalid() {
        LocaleUtils.getValidCountryCode("SDF");
    }

    @Test(expected = CoreException.class)
    public void countryCodeEmpty() {
        LocaleUtils.getValidCountryCode("");
    }

    @Test(expected = CoreException.class)
    public void countryCodeNull() {
        LocaleUtils.getValidCountryCode(null);
    }
}
