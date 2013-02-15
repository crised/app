package web;

import enums.Price;
import enums.Surface;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 * Date: 2/15/13
 * Time: 2:24 PM
 */

@ManagedBean
public class Enum {

    private String selection;
    private Integer sel;

    static final Logger log = Logger.getLogger(Enum.class);

    public Price[] getPriceArray() {
        return Price.values();
    }

    public Surface[] getSurfaceArray(){
        Surface[] hola = Surface.values();
        return hola;
        // return Surface.values();
    }

    public void actionListener(){

        log.info(selection);
        log.info(sel);


    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public Integer getSel() {
        return sel;
    }

    public void setSel(Integer sel) {
        this.sel = sel;
    }
}
