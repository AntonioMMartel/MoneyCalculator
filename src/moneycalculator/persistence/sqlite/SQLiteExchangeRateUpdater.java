package moneycalculator.persistence.sqlite;

import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import moneycalculator.Models.Currency;
import moneycalculator.persistence.DatabaseConnector;
import moneycalculator.persistence.DatabaseExchangeRateUpdater;

/**
 * @author Antonio Miguel Martel
 */
public class SQLiteExchangeRateUpdater implements DatabaseExchangeRateUpdater{
    
    @Override
    public boolean isUpdated(DatabaseConnector connection, Currency from, Currency to) {
        String query = "SELECT LAST_UPDATED FROM " + from.getIsoCode() + "_EXCHANGE"
            + " WHERE CURRENCY_ISO_CODE = '" + to.getIsoCode() + "';";
        
        try {
            Connection connect = connection.connect();
            Statement statement = connect.createStatement();
            
            ResultSet exchangeTable = statement.executeQuery(query);
            boolean result = exchangeTable.getString(1).equals(Date.valueOf(LocalDate.now()).toString());
            connection.disconnect(connect);
            
            return result;
        } catch (SQLException e) {
            System.out.println("Error con la extraccion de datos de la base de datos." + e.getMessage());
            System.out.println("Interrumpiendo ejecucion");
            exit(0);
        }
        
        return false;
    }

    @Override
    public void update(DatabaseConnector connection, Currency from, Currency to, double amount) {
        String query = "UPDATE " + from.getIsoCode() + "_EXCHANGE "
            + "SET EXCHANGE=" + amount + ",LAST_UPDATED='"
            + Date.valueOf(LocalDate.now()).toString()
            + "' WHERE CURRENCY_ISO_CODE = '" + to.getIsoCode() + "'";
        
        try {
            Connection connect = connection.connect();
            Statement statement = connect.createStatement();
            
            statement.executeUpdate(query);
            
            connect.setAutoCommit(false);
            connect.commit();
            
            connection.disconnect(connect);
        } catch (SQLException e) {
            System.out.println("Error con la actualizacion de los datos de la base de datos." + e.getMessage());
            System.out.println("Interrumpiendo ejecucion");
            exit(0);
        }
    }
    
}
