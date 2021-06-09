package receipt;

public class ItemExempt extends Item {

    public ItemExempt(int quantity, String description, double price){
        super(quantity, description, price);
    }

    public double calculateBasicSalesTax() {
        return 0;
    }

}
