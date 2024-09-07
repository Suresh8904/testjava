import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverterAPI {

    // API URL and Key
    private static final String API_KEY = "e2af22605727735f920a2f3f";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Currency Converter!");

        // Get live exchange rates
        Map<String, Double> exchangeRates = getLiveExchangeRates();
        if (exchangeRates == null) {
            System.out.println("Unable to fetch exchange rates.");
            return;
        }

        // Get user input
        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        System.out.println("Currencies available: " + exchangeRates.keySet());
        System.out.print("From (currency code): ");
        String fromCurrency = scanner.next().toUpperCase();
        System.out.print("To (currency code): ");
        String toCurrency = scanner.next().toUpperCase();

        // Perform conversion
        double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency, exchangeRates);
        if (convertedAmount == -1) {
            System.out.println("Invalid currency code. Please try again.");
        } else {
            System.out.printf("%.2f %s is equivalent to %.2f %s.%n", amount, fromCurrency, convertedAmount, toCurrency);
        }
    }

    // Fetch live exchange rates from the API
    public static Map<String, Double> getLiveExchangeRates() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response
            JSONObject json = new JSONObject(response.toString());
            JSONObject rates = json.getJSONObject("conversion_rates");

            // Convert rates to a Map
            Map<String, Double> exchangeRates = new HashMap<>();
            for (String key : rates.keySet()) {
                exchangeRates.put(key, rates.getDouble(key));
            }

            return exchangeRates;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static double convertCurrency(double amount, String fromCurrency, String toCurrency, Map<String, Double> exchangeRates) {
        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            return -1;
        }

        // Convert amount to USD
        double amountInUsd = amount / exchangeRates.get(fromCurrency);

        // Convert USD to target currency
        return amountInUsd * exchangeRates.get(toCurrency);
    }
}