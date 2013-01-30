package exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class AppException extends RuntimeException {

    public AppException(String message) {
        super(message);
    }
}
