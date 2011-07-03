package exceptions;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class CoreException extends RuntimeException {

    public CoreException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CoreException(String s) {
        super(s);
    }
}
