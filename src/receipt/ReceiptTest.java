package receipt;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ReceiptTest {
    private Receipt receipt;
    private ItemNormal item0 = new ItemNormal(0,"Goods 1",1.0);

    @Before
    public void setUp() throws Exception {
        receipt = new Receipt();
    }
    
    @Test
    public void emptyReceipt_SalesTax0_Total0() throws Exception {
       assertSalesTaxAndTotal(0,0);
    }

    @Test
    public void oneItem0Quantity_SalesTax0_Total0() throws Exception {
        receipt.add(item0);
        assertSalesTaxAndTotal(0,0);
    }

    private void assertSalesTaxAndTotal(int salesTax, int total) {
        assertEquals(salesTax, receipt.getSalesTax());
        assertEquals(total, receipt.getTotal());
    }
    
}
