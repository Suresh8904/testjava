// File: FinancialInstrument.java
public class FinancialInstrument {
    private String type;
    private String name;
    private int quantity;
    private double price;

    public FinancialInstrument(String type, String name, int quantity, double price) {
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getValue() {
        return quantity * price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return type + ": " + name + ", Quantity: " + quantity + ", Price per unit: " + price;
    }
}