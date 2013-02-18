package enums;


/**
 * Date: 2/15/13
 * Time: 2:06 PM
 */
public enum Surface {

    ALL("Todas las Superficies", null, null),
    FIRST("2 - 5 Ha.", 2f, 5f),
    SECOND("5 - 10 Ha.", 5f, 10f),
    THIRD("10 - 20 Ha.", 10f, 20f),
    FOURTH("20 - 50 Ha.", 20f, 50f),
    FIFTH("50 - 100 Ha.", 50f, 100f),
    SIXTH("Sóbre 100 Ha.", 100f, null);

    private String label;
    private Float lowerSurface, higherSurface;

    private Surface(String label, Float lowerSurface, Float higherSurface) {
        this.label = label;
        this.lowerSurface = lowerSurface;
        this.higherSurface = higherSurface;
    }

    public String getLabel() {
        return label;
    }

    public Float getLowerSurface() {
        return lowerSurface;
    }

    public Float getHigherSurface() {
        return higherSurface;
    }
}
