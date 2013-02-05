package exception;

import javax.ejb.ApplicationException;

/**
 * Date: 2/4/13
 * Time: 9:07 PM
 */

@ApplicationException
public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }
}
