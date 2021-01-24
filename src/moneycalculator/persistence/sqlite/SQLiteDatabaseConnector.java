package moneycalculator.persistence.sqlite;

import static java.lang.System.exit;
import moneycalculator.persistence.DatabaseConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Antonio Miguel Martel
 * 
 * Misma implementacion que con la kata5 pero separamos la coneccion de
 * la obtencion de datos.
 */
public class SQLiteDatabaseConnector implements DatabaseConnector {
    
        private final String databaseName;
    
    public SQLiteDatabaseConnector(String databaseName) {
        this.databaseName = databaseName;
    }
            
    @Override
    public Connection connect() {
        Connection connection = null;
        
        try {    
            String url = "jdbc:sqlite:" + databaseName;
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Ha habido un problema para conectarse a la base de datos." + e.getMessage());
            System.out.println("Interrumpiendo ejecucion..");
            exit(0);
        }
        
        return connection;
    }

    @Override
    public void disconnect(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ha habido un problema para desconectarse de la base de datos." + e.getMessage());
            System.out.println("Interrumpiendo ejecucion..");
            exit(0);
        }
    }
    
    
    public String getDatabaseName() {
        return this.databaseName;
    }
}