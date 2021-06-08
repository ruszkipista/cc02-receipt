package receipt;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private double salesTax = 0.0;
    private double total = 0.0;
    private List<ItemNormal> items = new ArrayList<ItemNormal>();

    public double getSalesTax(){
        return salesTax;
    }

    public double getTotal(){
        return total;
    }

    public void add(ItemNormal item) {
        double tax = item.calculateBasicSalesTax();
        double netValue = item.calculateValue();
        salesTax +=  tax;
        total +=  netValue + tax;
        items.add(item);
    }

}
