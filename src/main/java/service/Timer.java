package service;

import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

    @PostConstruct
    public void build(){
        log.info("Timer Singleton just built");
    }

    @PreDestroy
    public void destroy(){
        log.info("Timer Singleton just destroyed");
    }
}
