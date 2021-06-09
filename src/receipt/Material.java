package receipt;

public class Material {
    private String description;
    private double basePrice;
    private double basicSalesTaxRate;
    private double inportDutyRate;

    public Material(String description,
                    double basePrice,
                    double basicSalesTaxRate, 
                    double inportDutyRate){
        this.description = description;
        this.basePrice = basePrice;
        this.basicSalesTaxRate = basicSalesTaxRate;
        this.inportDutyRate = inportDutyRate;
    }

    public String getDescription() {return this.description;}
    public double getBasePrice() {return this.basePrice;}
    public double getBasicSalesTaxRate() {return this.basicSalesTaxRate;}
    public double getInportDutyRate() {return this.inportDutyRate;}

    public static double roundSalesTaxTo5c(double tax) {
        // https://stackoverflow.com/questions/9256005/java-rounding-to-nearest-0-05
        return Math.round(tax * 20.0)/20.0;
    }

    public double calculateSalesTax(int soldQuantity, double salePrice){
        double salesTax = 0;
        double netValue = soldQuantity * salePrice;
        salesTax += netValue * basicSalesTaxRate;
        salesTax += netValue * inportDutyRate;
        return salesTax;
    }
}
