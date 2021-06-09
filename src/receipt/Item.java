package receipt;

public abstract class Item {
    private int quantity;
    private String description;
    private double price;

    public Item(int quantity, String description, double price){
        this.quantity = quantity;
        this.description = description;
        this.price = price;
    }
		
    public double calculateValue() {
        return quantity * price;
    }

    public static double roundSalesTax(double tax) {
        // https://stackoverflow.com/questions/9256005/java-rounding-to-nearest-0-05
        return Math.round(tax * 20.0)/20.0;
    }

  public abstract double calculateBasicSalesTax();

}
