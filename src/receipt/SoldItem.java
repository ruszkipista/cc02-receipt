package receipt;

public class SoldItem {
    private Material material;
    private int quantity;
    private double salePrice;

    public SoldItem(Material material, int quantity, double salePrice){
        this.quantity = quantity;
        this.material = material;
        this.salePrice = salePrice;
    }
		
    public double calculateNetValue() {
        return quantity * salePrice;
    }

  public double calculateSalesTax(){
      return SalesTax.calculate(material, quantity, salePrice);
  }

}
