package receipt;

public enum TaxImportDuty {
    IMPORT(0.05), 
    LOCAL(0.0);

    public final double rate;

    private TaxImportDuty(double rate) {
        this.rate = rate;
    }

    public static double calculate(double netValue, double taxRate) {
        return netValue * taxRate;
    }
}