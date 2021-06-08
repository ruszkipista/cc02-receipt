package receipt;

public class ItemNormal {
   private int quantity;
   private String description;
   private int price;

   public ItemNormal(int quantity, String description, double price){
       this.quantity = quantity;
       this.description = description;
       this.price = (int) price*100;
   }

}
