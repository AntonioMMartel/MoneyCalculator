package moneycalculator;

import moneycalculator.control.FileCurrencyListLoader;
import moneycalculator.persistence.CurrencyListLoader;
import moneycalculator.view.ui.MoneyCalculatorFrame;

/**
 * @author Antonio Miguel Martel
 */
public class MoneyCalculator {

    public static void main(String[] args) {
        CurrencyListLoader currencyListLoader = new FileCurrencyListLoader("datafiles/currencies.txt");
        
        MoneyCalculatorFrame frame = new MoneyCalculatorFrame(currencyListLoader.loadCurrencies());
        
    }

}
