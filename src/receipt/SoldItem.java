package receipt;

public class SoldItem {
    private Material material;
    private int soldQuantity;
    private double salePrice;

    public SoldItem(Material material, int soldQuantity, double salePrice) {
        this.soldQuantity = soldQuantity;
        this.material = material;
        this.salePrice = salePrice;
    }

    public int getSoldQuantity() {
        return this.soldQuantity;
    }

    public String getDescription() {
        return material.getDescription();
    }

    public double getImportDutyRate() {
        return material.getImportDutyRate();
    }

    public double calculateNetValue() {
        return soldQuantity * salePrice;
    }
    
    public double calculateSalesTax() {
        return SalesTax.calculate(soldQuantity, salePrice, 
                material.getBasicSalesTaxRate(),
                material.getImportDutyRate());
    }

}
