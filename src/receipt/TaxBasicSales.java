package receipt;

public enum TaxBasicSales {
    NORMAL(0.1), 
    EXEMPT(0.0);

    public final double rate;

    private TaxBasicSales(double rate) {
        this.rate = rate;
    }

    public static double calculate(double netValue, double taxRate) {
        return netValue * taxRate;
    }

}