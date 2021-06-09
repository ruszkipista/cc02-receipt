package receipt;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
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
        double salesTax = item.calculateSalesTax();
        double netValue = item.calculateNetValue();
        this.salesTaxTotal += salesTax;
        this.valueTotal += netValue + salesTax;
        items.add(item);
    }

    public String makeReceipt(){
        return String.format("Sales Taxes: %s0.00\n", currencySign) +
               String.format("Total: £0.00", currencySign);
    }

}