package moneycalculator.Models;

/**
 * @author Antonio Miguel Martel
 */
public class Money {
    private final Currency currency;
    private final double value;

    public Money(Currency currency, double value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Double getValue() {
        return value;
    }
}
