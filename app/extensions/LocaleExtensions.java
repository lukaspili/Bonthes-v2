package extensions;

import play.i18n.Lang;
import play.i18n.Messages;

import java.util.Locale;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class LocaleExtensions {

    public static String localeCountry(String country) {
        return new Locale(Lang.get(), country).getDisplayCountry(Lang.getLocale());
    }

    public static String localSocialType(String socialType) {
        return Messages.get("socialType." + socialType);
    }

    public static String localeProfile(String profile) {
        return Messages.get("profile." + profile);
    }
}
