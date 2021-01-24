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
import moneycalculator.control.Command;
import moneycalculator.view.ui.swing.SwingMoneyDialog;

/**
 * @author Antonio Miguel Martel
 */
public class MoneyCalculatorFrame extends JFrame {
    
    private final List<Currency> currencies;
    private final Map<String, Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private MoneyDialog moneyDialog;

    public MoneyCalculatorFrame(List<Currency> currencies) {
        super.setTitle("Money Calculator");
        this.currencies = currencies;
        
        initComponents();

        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setResizable(true);
        super.setVisible(true); 

    }
    
    private void initComponents() {
        
        this.setLayout(new GridLayout(3, 1));
        SwingMoneyDialog moneyDialogUp = new SwingMoneyDialog(currencies);
        SwingMoneyDialog moneyDialogDown = new SwingMoneyDialog(currencies);
        
        this.add(moneyDialogUp);
        this.add(moneyDialogDown);
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
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                
            }
        };
    }
    
    
    
}
