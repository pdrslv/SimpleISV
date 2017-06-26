package ca.appdir.demo.simpleisv;

public class SimpleIsvException extends RuntimeException {

    public SimpleIsvException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimpleIsvException(Throwable cause) {
        super(cause);
    }
}
