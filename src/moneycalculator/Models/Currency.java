package moneycalculator.Models;

/**
 * @author Antonio Miguel Martel
 */
public class Currency {
    private final String name;
    private final String isoCode;
    private final String symbol;

    public Currency(String name, String isoCode, String symbol) {
        this.name = name;
        this.isoCode = isoCode;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getIsoCode() {
        return isoCode;
    }
    
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return name  + " - " + isoCode + "(" + symbol + ")";
    }
}
