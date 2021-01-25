package moneycalculator.persistence.sqlite;

import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import moneycalculator.Models.Currency;
import moneycalculator.persistence.CurrencyListLoader;

/**
 * @author Antonio Miguel Martel
 * 
 * Misma implementacion de la kata5 pero separada para mantener 
 * la cohesion.
 */
public class SQLiteCurrencyListLoader implements CurrencyListLoader{
    private final SQLiteConnector connector;

    public SQLiteCurrencyListLoader(String databaseName) {
        this.connector = new SQLiteConnector(databaseName);
    }
    
    private static ResultSet executeQuery(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM CURRENCIES");
    }
    
    private static Currency getCurrency(ResultSet currenciesTable) throws SQLException {
        String name = currenciesTable.getString(1);
        String isoCode = currenciesTable.getString(2);
        String symbol = currenciesTable.getString(3);
        return new Currency(name, isoCode, symbol);
    }
    
    @Override
    public List<Currency> loadCurrencies() {
        List<Currency> currencies = new LinkedList<>();
        Connection connection = connector.connect();
        
        try {
            ResultSet currenciesTable = executeQuery(connection);
            
            while (currenciesTable.next()) {
                currencies.add(getCurrency(currenciesTable));
            }
        } catch (SQLException e) {
            System.out.println("Error con la extraccion de datos de la base de datos." + e.getMessage());
            System.out.println("Interrumpiendo ejecucion");
            exit(0);
        }
        
        connector.disconnect(connection);
        return currencies;
    }
    
    @Override
    public Currency setExchangeCurrency(String isoCode) {
        Connection connection = connector.connect();
        
        try {
            ResultSet currenciesTable = executeQuery(connection);
            
            while (currenciesTable.next()) {
                Currency currency = getCurrency(currenciesTable);
                
                if (currency.getIsoCode().equals(isoCode)) {
                    connector.disconnect(connection);
                    return currency;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error con la extraccion de datos de la base de datos." + e.getMessage());
            System.out.println("Interrumpiendo ejecucion");
            exit(0);
        }
        
        connector.disconnect(connection);
        return null;
    }
}
