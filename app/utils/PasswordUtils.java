package utils;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class PasswordUtils {

    public static class PasswordEncrypted {

        public String hash;
        public String salt;

        public PasswordEncrypted(String hash, String salt) {
            this.hash = hash;
            this.salt = salt;
        }
    }

    public static PasswordEncrypted encryptWithSalt(String password) {
        return new PasswordEncrypted(password, password);
    }

    public static String encrypt(String password) {
        return password;
    }
}
