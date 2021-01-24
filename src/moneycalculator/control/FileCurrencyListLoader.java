package moneycalculator.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import moneycalculator.Models.Currency;
import moneycalculator.persistence.CurrencyListLoader;

/**
 * @author Antonio Miguel Martel
 */
public class FileCurrencyListLoader implements CurrencyListLoader{

    private final String fileName;

    public FileCurrencyListLoader(String fileName) {
        this.fileName = fileName;
    }
    
    
    @Override
    public List<Currency> loadCurrencies() {
        List<Currency> lista = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.fileName)));
            while(true) {
                String line = reader.readLine();
                if (line == null) break;
                lista.add(currencyOf(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: el archivo no existe." + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: fallo al leer el archivo" + e.getMessage());
        }
        
        
        
        return lista;
    }

    private Currency currencyOf(String line) {
        String[] data = line.split(";");
        try {
            return new Currency(data[0], data[1], data[2]);
        } catch (java.lang.ArrayIndexOutOfBoundsException e ){
            return new Currency(data[0], data[1], "");
        }
        
    }
    
    
    
}
