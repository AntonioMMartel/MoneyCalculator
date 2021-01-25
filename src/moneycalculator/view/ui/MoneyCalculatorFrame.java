package moneycalculator.view.ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import moneycalculator.Models.Currency;
import moneycalculator.control.CalculateCommand;
import moneycalculator.control.Command;
import moneycalculator.persistence.CurrencyListLoader;
import moneycalculator.view.ui.swing.SwingMoneyComponent;

/**
 * @author Antonio Miguel Martel
 */
public class MoneyCalculatorFrame extends JFrame {
    
    private final List<Currency> currencies; 
    private final Map<String, Command> commands = new HashMap<>();
    SwingMoneyComponent moneyComponentUp;
    SwingMoneyComponent moneyComponentDown;
    

    public MoneyCalculatorFrame(CurrencyListLoader currencyListLoader) {
        super.setTitle("Money Calculator");
        currencies = currencyListLoader.loadCurrencies();
        
        initComponents();
        
        commands.put("calculate", new CalculateCommand(moneyComponentUp, moneyComponentDown,currencyListLoader.setExchangeCurrency("EUR")));

        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setResizable(true);
        super.setVisible(true); 

    }
    
    private void initComponents() {
        
        this.setLayout(new GridLayout(3, 1));
        moneyComponentUp = new SwingMoneyComponent(currencies);
        moneyComponentDown = new SwingMoneyComponent(currencies);
        moneyComponentDown.getjTextFieldAmount().setEditable(false);
        this.add(moneyComponentUp);
        this.add(moneyComponentDown);
        this.add(toolbar());
        this.pack();
        
    }
    
    
    private Component toolbar() {
        JPanel toolbar = new JPanel();
        toolbar.add(calculateButton());
        return toolbar;
        
    }
    
    private JButton calculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(calculate());
        return button;
        
    }

    private ActionListener calculate() {
        return (ActionEvent e) -> {
            commands.get("calculate").execute();
        };
    }

}
