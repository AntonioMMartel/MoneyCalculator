package moneycalculator.Models;

/**
 * @author Antonio Miguel Martel
 */
public class Money {
    private final Currency currency;
    private final double amount;

    public Money(Currency currency, double value) {
        this.currency = currency;
        this.amount = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Double getAmount() {
        return amount;
    }
}
