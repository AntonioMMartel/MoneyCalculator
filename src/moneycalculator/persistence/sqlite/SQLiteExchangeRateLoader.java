/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package moneycalculator.persistence.sqlite;

import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import moneycalculator.Models.Currency;
import moneycalculator.Models.ExchangeRate;
import moneycalculator.persistence.DatabaseConnector;
import moneycalculator.persistence.DatabaseExchangeRateUpdater;
import moneycalculator.persistence.ExchangeRateLoader;

/**
 * @author Antonio Miguel Martel
 */
public class SQLiteExchangeRateLoader implements ExchangeRateLoader {
    private final DatabaseConnector connector;
    private final DatabaseExchangeRateUpdater manager;

    public SQLiteExchangeRateLoader(String databaseName) {
        this.connector = new SQLiteConnector(databaseName);
        this.manager = new SQLiteExchangeRateUpdater();
    }
    
    private static ResultSet executeQuery(Connection connection, Currency from, Currency to) throws SQLException {
        Statement statement = connection.createStatement();
        String a = "SELECT * FROM " + from.getIsoCode() + "_EXCHANGE WHERE CURRENCY_ISO_CODE='" + to.getIsoCode() + "';";
        return statement.executeQuery("SELECT EXCHANGE FROM " + from.getIsoCode() + "_EXCHANGE WHERE CURRENCY_ISO_CODE='" + to.getIsoCode() + "';");
    }
    
    private static ExchangeRate getExchange(Currency from, Currency to, ResultSet exchangeTable) throws SQLException {
        return new ExchangeRate(from, to, exchangeTable.getDouble(1));
    }
    
    private void updateRowExchangeTable(Currency from, Currency to) throws SQLException {

    }
    
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        Connection connection = connector.connect();
         
        try {
            updateRowExchangeTable(from, to);
            
            connection = connector.connect();
            ResultSet exchangeTable = executeQuery(connection, from, to);
            ExchangeRate exchange = getExchange(from, to, exchangeTable);
            
            connector.disconnect(connection);
            return exchange;
        } catch (SQLException e) {
            System.out.println("Error con la extraccion de datos de la base de datos" + e.getMessage());
            System.out.println("Interrumpiendo ejecucion..");
            exit(0);
        }
        
        connector.disconnect(connection);
        return null;
    }
}
