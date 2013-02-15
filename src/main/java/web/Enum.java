package web;

import enums.Price;
import enums.Surface;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Date: 2/15/13
 * Time: 2:24 PM
 */

@Named
@RequestScoped
public class Enum {

    public Price[] getPriceArray() {
        return Price.values();
    }

    public Surface[] getSurfaceArray(){
        Surface[] hola = Surface.values();
        return hola;
        // return Surface.values();
    }


}
