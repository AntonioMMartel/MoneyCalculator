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
import moneycalculator.view.ui.MoneyDialog;

/**
 * @author Antonio Miguel Martel
 */
public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    
    private final Currency[] currencies;
    private String amount;
    private Currency currency;

    public SwingMoneyDialog(List<Currency> currencies) {
        this.currencies = currencies.toArray(new Currency[0]);
        this.add(amount());
        this.add(currency());
    }

    @Override
    public Money get() {
        return new Money(currency, 0);
    }

    private Component amount() {
        JTextField textField = new JTextField("100");
        textField.setColumns(10);
        textField.getDocument().addDocumentListener(amountChanged());
        amount = textField.getText();
        return textField;
        
    }

    private Component currency() {
        JComboBox comboBox = new JComboBox(currencies);
        comboBox.addItemListener(currencyChanged());
        currency = (Currency) comboBox.getSelectedItem();
        return comboBox;
    }
    
    
    /*
        Esta lógica deberia de ir en otra clase.
        El display no tiene nada que ver con el 
        control de eventos.
    */
    
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
    
    
    
}
