// File: Portfolio.java
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, FinancialInstrument> instruments = new HashMap<>();

    public void addInstrument(FinancialInstrument instrument) {
        instruments.put(instrument.getName(), instrument);
    }

    public void removeInstrument(String name) {
        instruments.remove(name);
    }

    public FinancialInstrument getInstrument(String name) {
        return instruments.get(name);
    }

    public void displayPortfolio() {
        if (instruments.isEmpty()) {
            System.out.println("Portfolio is empty.");
        } else {
            System.out.println("Current Portfolio:");
            for (FinancialInstrument instrument : instruments.values()) {
                System.out.println(instrument);
            }
        }
    }

    public double calculateTotalValue() {
        double totalValue = 0.0;
        for (FinancialInstrument instrument : instruments.values()) {
            totalValue += instrument.getValue();
        }
        return totalValue;
    }

    public void savePortfolioToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (FinancialInstrument instrument : instruments.values()) {
                writer.write(instrument.getType() + "," + instrument.getName() + "," + instrument.getQuantity() + "," + instrument.getPrice());
                writer.newLine();
            }
            System.out.println("Portfolio saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving portfolio to file: " + e.getMessage());
        }
    }

    public void loadPortfolioFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String name = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                double price = Double.parseDouble(parts[3]);
                addInstrument(new FinancialInstrument(type, name, quantity, price));
            }
            System.out.println("Portfolio loaded from " + fileName);
        } catch (IOException e) {
            System.out.println("Error loading portfolio from file: " + e.getMessage());
        }
    }
}