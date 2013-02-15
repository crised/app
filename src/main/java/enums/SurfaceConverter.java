package enums;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

/**
 * Date: 2/15/13
 * Time: 3:20 PM
 */

@FacesConverter(value="surfaceConverter")
public class SurfaceConverter extends EnumConverter {

    public SurfaceConverter() {
        super(Surface.class);
    }
}
