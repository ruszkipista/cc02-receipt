package receipt;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private double salesTaxTotal = 0.0;
    private double total = 0.0;
    private List<Item> items = new ArrayList<Item>();

    public double getSalesTax(){
        return this.salesTaxTotal;
    }

    public double getTotal(){
        return this.total;
    }

    public void add(Item item) {
        double salesTax = item.calculateBasicSalesTax();
        double netValue = item.calculateValue();
        this.salesTaxTotal +=  salesTax;
        this.total +=  netValue + salesTax;
        items.add(item);
    }

}
