package coordinates;

public class CoordinateException extends Exception {

    public CoordinateException() {
    }

    public CoordinateException(String message) {
        super(message);
    }

    public CoordinateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoordinateException(Throwable cause) {
        super(cause);
    }

    public CoordinateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
