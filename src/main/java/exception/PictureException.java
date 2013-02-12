package exception;

import javax.ejb.ApplicationException;

/**
 * Date: 2/9/13
 * Time: 10:15 AM
 */

@ApplicationException
public class PictureException extends RuntimeException {

    public PictureException(String message) {
        super(message);
    }
}
