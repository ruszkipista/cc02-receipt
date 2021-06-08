package receipt;

public class Receipt {
    private int salesTax = 0;
    private int total = 0;
    private int items[];

    public int getSalesTax(){
        return salesTax;
    }

    public int getTotal(){
        return total;
    }
}
