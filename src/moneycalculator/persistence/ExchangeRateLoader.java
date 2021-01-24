package moneycalculator.persistence;

import moneycalculator.Models.Currency;
import moneycalculator.Models.ExchangeRate;

/**
 * @author Antonio Miguel Martel
 */
public interface ExchangeRateLoader {
    
    public ExchangeRate load(Currency from, Currency to);
    
}
