package moneycalculator.view.ui.swing;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import moneycalculator.Models.Money;
import moneycalculator.view.ui.MoneyDisplay;

/**
 * @author Antonio Miguel Martel
 * 15 05
 */
public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {
    
    private Money money;
    
    @Override
    public void display(Money money) {
        this.money = money;
        this.removeAll();
        this.add(amount());
        this.add(currency());
        this.updateUI();
 
    }
    
    private Component amount() {
        return new JLabel(String.valueOf(money.getAmount()));
    }
    
    private Component currency() {
        return new JLabel(this.money.getCurrency().getIsoCode());
    }
    
}