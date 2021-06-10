package receipt;

public class Material {
    private String description;
    private double basePrice;
    private double basicSalesTaxRate;
    private double importDutyRate;

    public Material(String description, double basePrice, double basicSalesTaxRate, double importDutyRate) {
        this.description = description;
        this.basePrice = basePrice;
        this.basicSalesTaxRate = basicSalesTaxRate;
        this.importDutyRate = importDutyRate;
    }

    public String getDescription() {
        return this.description;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public double getBasicSalesTaxRate() {
        return this.basicSalesTaxRate;
    }

    public double getImportDutyRate() {
        return this.importDutyRate;
    }

}
