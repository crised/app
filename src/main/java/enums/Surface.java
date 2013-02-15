package enums;


/**
 * Date: 2/15/13
 * Time: 2:06 PM
 */
public enum Surface {

    ALL("Todos los precios", 0f, 0f),
    FIRST("2 - 5 Ha.", 2f, 5f),
    SECOND("5 - 10 Ha.", 5f, 10f),
    THIRD("10 - 20 Ha.", 10f, 20f),
    FOURTH("20 - 50 Ha.", 20f, 50f),
    FIFTH("50 - 100 Ha.", 50f, 100f),
    SIXTH("Sobre 100 Ha.", 100f, 0f);

    private String label;
    private float lowerSurface, higherSurface;

    private Surface(String label, Float lowerSurface, Float higherSurface) {
        this.label = label;
        this.lowerSurface = lowerSurface;
        this.higherSurface = higherSurface;
    }

    public String getLabel() {
        return label;
    }

    public float getLowerSurface() {
        return lowerSurface;
    }

    public float getHigherSurface() {
        return higherSurface;
    }
}
