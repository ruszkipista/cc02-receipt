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
		
    public double getNetValue() {
        return quantity * salePrice;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public String getDescription(){
        return material.getDescription();
    }

    public double getImportDutyRate(){
        return material.getImportDutyRate();
    }

    public double getSalesTax(){
        return SalesTax.calculate(material, quantity, salePrice);
    }

}
