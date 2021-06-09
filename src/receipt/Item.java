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

  public abstract double calculateBasicSalesTax();

}
