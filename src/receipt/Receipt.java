package receipt;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private double salesTaxTotal = 0.0;
    private double total = 0.0;
    private List<SoldItem> items = new ArrayList<SoldItem>();

    public double getSalesTax(){
        return this.salesTaxTotal;
    }

    public double getTotal(){
        return this.total;
    }

    public void add(SoldItem item) {
        double salesTax = item.calculateSalesTax();
        double netValue = item.calculateNetValue();
        this.salesTaxTotal +=  salesTax;
        this.total +=  netValue + salesTax;
        items.add(item);
    }

}
