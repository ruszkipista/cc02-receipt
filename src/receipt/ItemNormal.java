package receipt;

public class ItemNormal extends Item {

    public ItemNormal(int quantity, String description, double price){
        super(quantity, description, price);
    }

    public double calculateBasicSalesTax() {
        double tax = calculateValue() * 0.1;
        return roundSalesTax(tax);
    }

}
