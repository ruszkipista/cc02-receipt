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
    private Material materialNomalLocal  = new Material("product normal local", 2.0, 0.1, 0.0);
    private Material materialExemptLocal = new Material("product exempt local", 3.0, 0.0, 0.0);
    private Material materialNomalImport = new Material("product normal import", 5.0, 0.1, 0.05);
    private Material materialExemptImport= new Material("product exempt import", 7.0, 0.0, 0.05);

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
    public void oneItemNormalQuantity0Price1_SalesTax0_Total0() throws Exception {
        receipt.add(new SoldItem(materialNomalLocal, 0, 1.0));
        assertSalesTaxAndTotal(0.0, 0.0);
    }

    @Test
    public void oneItemNormalQuantity2Price0_SalesTax0_Total0() throws Exception {
        receipt.add(new SoldItem(materialNomalLocal, 2, 0.0));
        assertSalesTaxAndTotal(0.0, 0.0);
    }

    @Test
    public void oneItemNormalQuantity1Price2_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialNomalLocal, 3, materialNomalLocal.getBasePrice()));
        assertSalesTaxAndTotal(0.6, 6.6);
    }

    @Test
    public void twoNormalItems_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialNomalLocal, 2, 3.0));
        receipt.add(new SoldItem(materialNomalLocal, 5, 7.0));
        assertSalesTaxAndTotal(4.1, 45.1);
    }

    @Test
    public void oneItemExempt_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialExemptLocal, 2, 3.0));
        assertSalesTaxAndTotal(0.0, 6.0);
    }

    @Test
    public void salesTaxRoundToNearest5c() throws Exception {
        assertEquals(0,    Material.roundSalesTaxTo5c(0.0), EPSILON);
        assertEquals(0.95, Material.roundSalesTaxTo5c(0.93), EPSILON);
        assertEquals(0.8,  Material.roundSalesTaxTo5c(0.81), EPSILON);
        assertEquals(0.65, Material.roundSalesTaxTo5c(0.65), EPSILON);
        assertEquals(0.7,  Material.roundSalesTaxTo5c(0.68), EPSILON);
        assertEquals(0.65, Material.roundSalesTaxTo5c(0.67), EPSILON);
    }
}
