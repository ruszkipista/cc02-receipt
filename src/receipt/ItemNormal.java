package receipt;

public class ItemNormal extends Item {

    public ItemNormal(int quantity, String description, double price){
        super(quantity, description, price);
    }

    public double calculateBasicSalesTax() {
        return calculateValue() * 0.1;
    }

}
