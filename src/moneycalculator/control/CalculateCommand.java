package moneycalculator.control;

import moneycalculator.Models.Currency;
import moneycalculator.Models.ExchangeRate;
import moneycalculator.Models.Money;
import moneycalculator.persistence.ExchangeRateLoader;
import moneycalculator.persistence.sqlite.SQLiteExchangeRateLoader;
import moneycalculator.view.ui.MoneyComponent;

/**
 * @author Antonio Miguel Martel
 */
public class CalculateCommand implements Command {
    
    private final ExchangeRateLoader exchangeRateLoader;
    private MoneyComponent moneyComponentUp;
    private MoneyComponent moneyComponentDown;
    private Currency exchangeCurrency;

    public CalculateCommand(MoneyComponent moneyDialogUp, MoneyComponent moneyDialogDown, Currency exchangeCurrency) {
        this.moneyComponentUp = moneyDialogUp;
        this.moneyComponentDown = moneyDialogDown;
        this.exchangeCurrency = exchangeCurrency;
        exchangeRateLoader = new SQLiteExchangeRateLoader("database\\MoneyCalculatorDB.db");
    }
    
    @Override
    public void execute() {
        Money upMoney = moneyComponentUp.get();
        Money downMoney = moneyComponentDown.get();
        
        displayAmount(getExchange(upMoney, downMoney));
    }
    
    private void displayAmount(double amount) {
        moneyComponentDown.updateAmount(amount);
    }

    private double getExchange(Money upMoney, Money downMoney) {
        Currency upCurrency = upMoney.getCurrency();
        Currency downCurrency = downMoney.getCurrency();
        double newAmount;
        
        if (upCurrency.getIsoCode().equals("EUR")) {
            ExchangeRate exchange = exchangeRateLoader.load(upCurrency, downCurrency);
            newAmount = exchange.getAmount() * upMoney.getValue();
        } else if(downCurrency.getIsoCode().equals("EUR")) {
            ExchangeRate exchange = exchangeRateLoader.load(downCurrency, upCurrency);
            newAmount = upMoney.getValue() / exchange.getAmount();
        } else {
            ExchangeRate exchangeLeft = exchangeRateLoader.load(exchangeCurrency, upCurrency);
            ExchangeRate exchangeRight = exchangeRateLoader.load(exchangeCurrency, downCurrency);
            
            newAmount = upMoney.getValue() / exchangeLeft.getAmount();
            newAmount = newAmount * exchangeRight.getAmount();
        }
        
        return Math.round(newAmount * Math.pow(10, 3)) / Math.pow(10, 3);
    }
    
    
}
