package web;

import enums.Price;
import model.Ad;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Date: 2/12/13
 * Time: 4:22 PM
 */

@Named
@RequestScoped
public class ViewBean extends CacheView {

    static final Logger log2 = Logger.getLogger(ViewBean.class);

    private List<Ad> adSubList;


    //@PostConstruct
    public void init() {

        //adSubList = getSubList(0,5); // Get the first 6
        //adSubList = completeList();
        //log2.info("ViewBean Created");


    }




    //@PreDestroy
    public void destroy() {
       // log2.info("ViewBean Destroyed");
    }

    public List<Ad> getAdSubList() {
        return adSubList;
    }

    public void setAdSubList(List<Ad> adSubList) {
        this.adSubList = adSubList;
    }
}
