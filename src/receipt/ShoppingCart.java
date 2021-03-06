package receipt;

import java.util.ArrayList;
import java.util.List;

import java.text.NumberFormat;

public class ShoppingCart {
    private NumberFormat currencyFormat;
    private double salesTaxTotal = 0.0;
    private double valueTotal = 0.0;
    private List<SoldItem> items = new ArrayList<SoldItem>();

    public ShoppingCart(Currency currency) {
        this.currencyFormat = currency.currencyFormat;
    }

    public double getTotalSalesTax() {
        return this.salesTaxTotal;
    }

    public double getTotalValue() {
        return this.valueTotal;
    }

    public void add(SoldItem item) {
        double salesTax = item.calculateSalesTax();
        double netValue = item.calculateNetValue();
        this.salesTaxTotal += salesTax;
        this.valueTotal += netValue + salesTax;
        items.add(item);
    }

    public String makeReceipt() {
        return makeItems() + makeSummary();
    }

    private String makeItems() {
        String itemLines = "";
        for (SoldItem item : items)
            itemLines += makeItemLine(item);
        return itemLines;
    }

    private String makeItemLine(SoldItem item) {
        return String.format("%d ", item.getSoldQuantity()) 
                + ((item.getImportDutyRate() != TaxImportDuty.LOCAL.rate) ? "imported " : "")
                + item.getDescription()
                + String.format(": %s\n", currencyFormat.format(item.calculateNetValue() 
                + item.calculateSalesTax()));
    }

    private String makeSummary() {
        return String.format("Sales Taxes: %s\n", currencyFormat.format(this.salesTaxTotal))
                + String.format("Total: %s", currencyFormat.format(this.valueTotal));
    }

}
