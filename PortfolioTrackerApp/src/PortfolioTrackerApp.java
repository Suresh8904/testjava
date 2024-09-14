// File: PortfolioTrackerApp.java
import java.util.Scanner;

public class PortfolioTrackerApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Portfolio portfolio = new Portfolio();

    public static void main(String[] args) {
        String fileName = "portfolio_data.txt";
        portfolio.loadPortfolioFromFile(fileName);

        while (true) {
            System.out.println("\n1. Add Instrument");
            System.out.println("2. Remove Instrument");
            System.out.println("3. View Portfolio");
            System.out.println("4. Calculate Total Value");
            System.out.println("5. Save Portfolio");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline

            switch (choice) {
                case 1:
                    addInstrument();
                    break;
                case 2:
                    removeInstrument();
                    break;
                case 3:
                    portfolio.displayPortfolio();
                    break;
                case 4:
                    System.out.println("Total Portfolio Value: " + portfolio.calculateTotalValue());
                    break;
                case 5:
                    portfolio.savePortfolioToFile(fileName);
                    break;
                case 6:
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addInstrument() {
        System.out.print("Enter Instrument Type (Stock/Bond/Mutual Fund): ");
        String type = scanner.nextLine();
        System.out.print("Enter Instrument Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Price per Unit: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // consume the newline

        FinancialInstrument instrument = new FinancialInstrument(type, name, quantity, price);
        portfolio.addInstrument(instrument);
        System.out.println("Instrument added to portfolio.");
    }

    private static void removeInstrument() {
        System.out.print("Enter Instrument Name to Remove: ");
        String name = scanner.nextLine();
        if (portfolio.getInstrument(name) != null) {
            portfolio.removeInstrument(name);
            System.out.println("Instrument removed from portfolio.");
        } else {
            System.out.println("Instrument not found.");
        }
    }
}