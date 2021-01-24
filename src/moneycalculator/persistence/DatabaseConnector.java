/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moneycalculator.persistence;

import java.sql.Connection;

/**
 *
 * @author Antonio Miguel martel 
 */
public interface DatabaseConnector {

    public Connection connect();
    
    public void disconnect(Connection connection);
    
    
}
