package units.locales;

import extensions.LocaleExtensions;
import models.users.Profile;
import models.users.SocialType;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import play.i18n.Lang;
import play.test.UnitTest;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class LocaleFrTest extends UnitTest {

    @Before
    public void before() {
        Lang.set("fr");
        Assertions.assertThat("fr").isEqualTo(Lang.get());
    }

    @Test
    public void countries() {
        Assertions.assertThat(LocaleExtensions.localeCountry("GB")).isEqualTo("Royaume-Uni");
    }

    @Test
    public void profiles() {
        Assertions.assertThat(LocaleExtensions.localeProfile(Profile.CLIENT_STANDARD)).isEqualToIgnoringCase("client");
        Assertions.assertThat(LocaleExtensions.localeProfile(Profile.CLIENT_LOYAL)).isEqualToIgnoringCase("client fidèle");
        Assertions.assertThat(LocaleExtensions.localeProfile(Profile.ADMIN)).isEqualToIgnoringCase
                ("administrateur");
    }

    @Test
    public void socialTypes() {
        Assertions.assertThat(LocaleExtensions.localSocialType(SocialType.MLLE)).isEqualToIgnoringCase
                ("mlle");
        Assertions.assertThat(LocaleExtensions.localSocialType(SocialType.MME)).isEqualToIgnoringCase
                ("mme");
        Assertions.assertThat(LocaleExtensions.localSocialType(SocialType.MR)).isEqualToIgnoringCase
                ("m.");
    }
}
