package receipt;

public class SalesTax {
    public static double calculate(Material material, int soldQuantity, double salePrice){
        double salesTax = 0;
        double netValue = soldQuantity * salePrice;
        double basicSalesTax = netValue * material.getBasicSalesTaxRate();
        salesTax += basicSalesTax;
        double importDuty = netValue * material.getImportDutyRate();
        salesTax +=  importDuty;
        return roundAmountTo5c(salesTax);
    }

    public static double roundAmountTo5c(double amount) {
        // https://stackoverflow.com/questions/9256005/java-rounding-to-nearest-0-05
        return Math.round(amount * 20.0)/20.0;
    }
}
