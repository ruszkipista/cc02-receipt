package receipt;

public class ItemNormal {
   private int quantity;
   private String description;
   private double price;

   public ItemNormal(int quantity, String description, double price){
       this.quantity = quantity;
       this.description = description;
       this.price = price;
   }

    public double calculateValue() {
        return quantity * price;
    }

    public double calculateBasicSalesTax() {
        return calculateValue() * 0.1;
    }

}
