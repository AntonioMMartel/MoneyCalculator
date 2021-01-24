/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package moneycalculator.persistence;

import moneycalculator.Models.Currency;

/**
 * @author Antonio Miguel Martel
 */
public interface DatabaseExchangeRateUpdater {
    
    public boolean isUpdated(DatabaseConnector connection, Currency from, Currency to);
    
    public void update(DatabaseConnector connection, Currency from, Currency to, double amount);
    
    
}
