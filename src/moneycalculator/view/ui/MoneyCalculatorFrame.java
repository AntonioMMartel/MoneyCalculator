package moneycalculator.view.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import moneycalculator.Models.Currency;
import moneycalculator.view.ui.swing.SwingMoneyDialog;
import moneycalculator.view.ui.swing.SwingMoneyDisplay;

/**
 * @author Antonio Miguel Martel
 */
public class MoneyCalculatorFrame extends JFrame {
    
    private final List<Currency> currencies;
    
    private MoneyDisplay moneyDisplay;
    private MoneyDialog moneyDialog;

    public MoneyCalculatorFrame(List<Currency> currencies) {
        this.currencies = currencies;
        this.setTitle("Money calculator");
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(moneyDialog(currencies), BorderLayout.NORTH);
        this.add(moneyDisplay(), BorderLayout.SOUTH);
        this.add(toolbar(), BorderLayout.CENTER);
        this.setVisible(true);
    }

    private Component moneyDialog(List<Currency> currencies) {
        SwingMoneyDialog moneyDialog = new SwingMoneyDialog(currencies);
        this.moneyDialog = moneyDialog;
        return moneyDialog;
        
    }

    private Component moneyDisplay() {
        SwingMoneyDisplay moneyDisplay = new SwingMoneyDisplay();
        this.moneyDisplay = moneyDisplay;
        return moneyDisplay;
        
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
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        };
    }
    
    
    
}
