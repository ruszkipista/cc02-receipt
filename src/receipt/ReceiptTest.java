package receipt;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;;

public class ReceiptTest {
    private final double EPSILON = 1e-5;
    private Receipt receipt;
    private ItemNormal item0normal = new ItemNormal(0,"Goods 0",1.0);
    private ItemNormal item1normal = new ItemNormal(1,"Goods 1",0.0);
    private ItemNormal item2normal = new ItemNormal(1,"Goods 2",2.0);
    private ItemNormal item3normal = new ItemNormal(3,"Goods 3",3.0);

    private void assertSalesTaxAndTotal(double salesTax, double total) {
        assertEquals(salesTax, receipt.getSalesTax(), EPSILON);
        assertEquals(total, receipt.getTotal(), EPSILON);
    }

    @Before
    public void setUp() throws Exception {
        receipt = new Receipt();
    }
    
    @Test
    public void emptyReceipt_SalesTax0_Total0() throws Exception {
       assertSalesTaxAndTotal(0.0, 0.0);
    }

    @Test
    public void oneItem0Quantity0Price_SalesTax0_Total0() throws Exception {
        receipt.add(item0normal);
        assertSalesTaxAndTotal(0.0, 0.0);
    }

    @Test
    public void oneItem2Quantity0Price_SalesTax0_Total0() throws Exception {
        receipt.add(item1normal);
        assertSalesTaxAndTotal(0.0, 0.0);
    }

    @Test
    public void oneItem1Quantity_SalesTaxTotal() throws Exception {
        receipt.add(item2normal);
        assertSalesTaxAndTotal(0.2, 2.2);
    }

    @Test
    public void twoNormalItems_SalesTaxTotal() throws Exception {
        receipt.add(item2normal);
        receipt.add(item3normal);
        assertSalesTaxAndTotal(1.1, 12.1);
    }
    
}
