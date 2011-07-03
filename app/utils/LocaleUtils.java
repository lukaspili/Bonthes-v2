package utils;

import exceptions.CoreException;
import play.i18n.Lang;

import java.util.Locale;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class LocaleUtils {

    public static String getValidCountryCode(String countryCode) {

        try {
            Locale locale = new Locale(Lang.get(), countryCode.toUpperCase());

            // throw an exception if locale isn't exists
            if (locale.getISO3Country().isEmpty()) {
                throw new RuntimeException();
            }

            return locale.getCountry();

        } catch (Exception e) {
            throw new CoreException("locale not found for country code : " + countryCode, e);
        }
    }
}
