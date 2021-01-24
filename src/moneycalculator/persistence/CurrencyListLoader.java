package moneycalculator.persistence;

import java.util.List;
import moneycalculator.Models.Currency;

/**
 * @author Antonio Miguel Martel
 */
public interface CurrencyListLoader {
    
    public List<Currency> loadCurrencies(); 
    
}
