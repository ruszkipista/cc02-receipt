package receipt;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private int salesTax = 0;
    private int total = 0;
    private List<ItemNormal> items = new ArrayList<ItemNormal>();

    public int getSalesTax(){
        return salesTax;
    }

    public int getTotal(){
        return total;
    }

    public void add(ItemNormal item) {
        items.add(item);
    }
}
