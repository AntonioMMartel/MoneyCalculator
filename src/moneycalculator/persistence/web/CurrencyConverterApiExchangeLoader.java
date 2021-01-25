package moneycalculator.persistence.web;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.exit;
import java.net.URL;
import java.net.URLConnection;
import moneycalculator.Models.Currency;
import moneycalculator.Models.ExchangeRate;
import moneycalculator.persistence.ExchangeRateLoader;

/**
 * @author Antonio Miguel Martel
 * 
 * Se utilizan los apuntes de JSON del campus y se adaptan a este problema.
 */
public class CurrencyConverterApiExchangeLoader implements ExchangeRateLoader{

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        String nameURL = "https://free.currconv.com/api/v7/convert?q="
            + from.getIsoCode()+ "_" + to.getIsoCode()
            + "&compact=ultra&apiKey=835507967e342389c616";
                
        try {
            URLConnection connection = new URL(nameURL).openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader input = new InputStreamReader(inputStream);
            
            try (BufferedReader reader = new BufferedReader(input)) {
                String value = readValue(reader);
                String jsonString = parseToJson(from, to, value);
                
                return new Gson().fromJson(jsonString, ExchangeRate.class);
            }
        } catch (IOException e) {
            System.out.println("No se han podido obtener los datos de la web" +e.getMessage());
            System.out.println("Interrumpiendo ejecucion..");
            exit(0);
        }
        
        return null;
    }

    private static String readValue(BufferedReader reader) throws IOException {
        String jsonLine = reader.readLine().split(":")[1];
        return jsonLine.substring(0, jsonLine.length() - 1);
    }

    private String parseToJson(Currency from, Currency to, String value) {
        return "{"
                +  "\"from\": {"
                +      "\"name\":\"" + from.getName() + "\","
                +      "\"isoCode\":\"" + from.getIsoCode() + "\","
                +      "\"symbol\":\"" + from.getSymbol() + "\""
                +   "},"
                +   "\"to\": {"
                +      "\"name\":\"" + to.getName() + "\","
                +      "\"isoCode\":\"" + to.getIsoCode() + "\","
                +      "\"symbol\":\"" + to.getSymbol() + "\""
                +   "},"
                +   "\"amount\":" + value
                + "}";
    }
}
