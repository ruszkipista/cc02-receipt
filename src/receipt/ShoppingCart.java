package receipt;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    public final static String CURRENCY_GBP_SIGN = "£";
    private String currencySign;
    private double salesTaxTotal = 0.0;
    private double valueTotal = 0.0;
    private List<SoldItem> items = new ArrayList<SoldItem>();

    public ShoppingCart(String currencySign){
        this.currencySign = currencySign;
    }

    public double getSalesTax(){
        return this.salesTaxTotal;
    }

    public double getTotal(){
        return this.valueTotal;
    }

    public void add(SoldItem item) {
        double salesTax = item.getSalesTax();
        double netValue = item.getNetValue();
        this.salesTaxTotal += salesTax;
        this.valueTotal += netValue + salesTax;
        items.add(item);
    }

    public String makeReceipt(){
        return makeItems() + makeSummary();
    }

    private String makeItems() {
        String itemLines = "";
        for (SoldItem item : items)
            itemLines += makeItemLine(item);
        return itemLines;
    }

    private String makeItemLine(SoldItem item) {
        return String.format("%d ", item.getQuantity()) +
               ((item.getImportDutyRate() != 0) ? "imported " : "") +
               item.getDescription() +
               String.format(" at %s%.2f\n", 
                            currencySign, 
                            item.getNetValue()+item.getSalesTax());
    }

    private String makeSummary() {
        return String.format("Sales Taxes: %s%.2f\n", currencySign, this.salesTaxTotal) +
               String.format("Total: %s%.2f", currencySign, this.valueTotal);
    }

}
