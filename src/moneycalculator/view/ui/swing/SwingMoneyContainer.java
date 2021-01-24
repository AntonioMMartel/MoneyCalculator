/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package moneycalculator.view.ui.swing;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import moneycalculator.Models.Currency;

/**
 * @author Antonio Miguel Martel
 */
public class SwingMoneyContainer extends JPanel {
    
    private JComboBox<Currency> currenciesComboBox;
    private JTextField amountTextField;
    private List<Currency> currencies;
    
    public SwingMoneyContainer(List<Currency> currencies) {
        this.currencies = currencies;
        initComponents();
    }

    private void initComponents() {
        
        
    }
    
    
}
