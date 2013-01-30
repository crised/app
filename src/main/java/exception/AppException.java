package exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class AppException extends Exception {

    public AppException(String message) {
        super(message);
    }
}
