package web;

import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Date: 2/21/13
 * Time: 5:13 PM
 */

@Named
@RequestScoped
public class Test {

    static final Logger log = Logger.getLogger(Test.class);

    public void hello(String h){

        log.info(h);

    }
}
