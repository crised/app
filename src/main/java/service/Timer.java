package service;

import org.jboss.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 * Date: 2/11/13
 * Time: 3:59 PM
 */

@Singleton
public class Timer {

    Logger log = Logger.getLogger(Timer.class);

    @Schedule(minute = "*/5", hour = "*")
    public void info() {

        log.info("5 minutes have passed :)");

    }
}
