package enums;

import java.math.BigDecimal;

/**
 * Date: 2/15/13
 * Time: 1:52 PM
 */
public enum Price {

    ALL("Todos los precios", null, null),
    FIRST("MM$5 - MM$15", new BigDecimal(5_000_000), new BigDecimal(10_000_000)),
    SECOND("MM$15 - MM$30", new BigDecimal(15_000_000), new BigDecimal(30_000_000)),
    THIRD("MM$30 - MM$60", new BigDecimal(30_000_000), new BigDecimal(60_000_000)),
    FOURTH("MM$60 - MM$100", new BigDecimal(60_000_000), new BigDecimal(100_000_000)),
    FIFTH("MM$100 - MM$200", new BigDecimal(100_000_000), new BigDecimal(200_000_000)),
    SIXTH("Sobre MM$200", new BigDecimal(200_000_000), null);

    private String label;
    private BigDecimal lowerPrice, higherPrice;      //Para el lowerprice siempre comparar con mayor o igual

    private Price(String label, BigDecimal lowerPrice, BigDecimal higherPrice) {
        this.label = label;
        this.lowerPrice = lowerPrice;
        this.higherPrice = higherPrice;
    }

    public String getLabel() {
        return label;
    }

    public BigDecimal getLowerPrice() {
        return lowerPrice;
    }

    public BigDecimal getHigherPrice() {
        return higherPrice;
    }
}
