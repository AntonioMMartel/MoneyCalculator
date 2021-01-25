package moneycalculator;

import moneycalculator.persistence.CurrencyListLoader;
import moneycalculator.persistence.sqlite.SQLiteCurrencyListLoader;
import moneycalculator.view.ui.MoneyCalculatorFrame;

/**
 * @author Antonio Miguel Martel
 */
public class MoneyCalculator {

    public static void main(String[] args) {
        CurrencyListLoader currencyListLoader = new SQLiteCurrencyListLoader("database\\MoneyCalculatorDB.db");
        MoneyCalculatorFrame frame = new MoneyCalculatorFrame(currencyListLoader);
    }

}
