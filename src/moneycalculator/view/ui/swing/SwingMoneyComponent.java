package moneycalculator.view.ui.swing;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import moneycalculator.Models.Currency;
import moneycalculator.Models.Money;
import moneycalculator.view.ui.MoneyComponent;

/**
 * @author Antonio Miguel Martel
 */
public class SwingMoneyComponent extends JPanel implements MoneyComponent {
    
    private final Currency[] currencies;
    
    private JComboBox<Currency> jComboBoxCurrencies;
    private JTextField jTextFieldAmount;

    public SwingMoneyComponent(List<Currency> currencies) {
        this.currencies = currencies.toArray(new Currency[0]);
        super.add(amount());
        super.add(currency());
    }

    @Override
    public Money get() {
        return new Money((Currency) jComboBoxCurrencies.getSelectedItem(), Double.parseDouble(jTextFieldAmount.getText()));
    }
    
    @Override
    public void updateAmount(double newAmount) {
        jTextFieldAmount.setText(Double.toString(newAmount));
        
    }
    
    private Component amount() {
        jTextFieldAmount = new JTextField();
        jTextFieldAmount.setColumns(10);
        //jTextFieldAmount.getDocument().addDocumentListener(amountChanged());
        return jTextFieldAmount;
        
    }

    private Component currency() {
        jComboBoxCurrencies = new JComboBox(currencies);
        //jComboBoxCurrencies.addItemListener(currencyChanged());
        return jComboBoxCurrencies;
    }
    
    
    /*
        Esta lógica deberia de ir en otra clase.
        El display no tiene nada que ver con el 
        control de eventos que sucede en el.
    
        Sin embargo como es una app pequeña nos podemos dar el lujo.
    
    
    private DocumentListener amountChanged() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                amountChanged(e.getDocument());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                amountChanged(e.getDocument());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                amountChanged(e.getDocument());
            }
            
            private void amountChanged (Document document) {
                try {
                    amount = document.getText(0, document.getLength());
                } catch (BadLocationException e) {
                    System.out.println("Error de la clase SwingMoneyDisplay (bad location)" + e.getMessage());
                }
            }
        };
    }

    private ItemListener currencyChanged() {
        return (ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.DESELECTED) return;
            currency = (Currency) e.getItem();
        };
    }
*/
    
    
    
    
}
