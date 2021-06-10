package receipt;

public final class SalesTax {

    public static double calculate(int soldQuantity, double salePrice, 
            double basicSalesTaxRate,
            double importDutyTaxRate) {
        double netValue = soldQuantity * salePrice;
        double basicSalesTax = TaxImportDuty.calculate(netValue, basicSalesTaxRate);
        double importDuty = TaxImportDuty.calculate(netValue, importDutyTaxRate);
        double salesTax = basicSalesTax + importDuty;
        return roundAmountTo5c(salesTax);
    }

    public static double roundAmountTo5c(double amount) {
        // https://stackoverflow.com/questions/9256005/java-rounding-to-nearest-0-05
        return Math.round(amount * 20.0) / 20.0;
    }
}
