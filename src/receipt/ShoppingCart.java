package receipt;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class ShoppingCart {

    private double salesTaxTotal = 0.0;
    private double valueTotal = 0.0;
    private List<SoldItem> items = new ArrayList<SoldItem>();

    public double getSalesTax(){
        return this.salesTaxTotal;
    }

    public double getTotal(){
        return this.valueTotal;
    }

    public void add(SoldItem item) {
        double salesTax = item.calculateSalesTax();
        double netValue = item.calculateNetValue();
        this.salesTaxTotal += salesTax;
        this.valueTotal += netValue + salesTax;
        items.add(item);
    }

    public String makeReceipt(){
        return "Sales Taxes: £0.00\n" +
               "Total: £0.00";
    }

}
