package receipt;

import org.junit.Test;
import org.junit.Before;

import java.text.NumberFormat;

import static org.junit.Assert.assertEquals;

public class ReceiptTest {
    private final double EPSILON = 1e-5;
    private ShoppingCart receipt;
    private Material materialNormalLocal = new Material("product normal", 2.0, TaxBasicSales.NORMAL.rate, TaxImportDuty.LOCAL.rate);
    private Material materialExemptLocal = new Material("product exempt", 3.0, TaxBasicSales.EXEMPT.rate, TaxImportDuty.LOCAL.rate);
    private Material materialNormalImport = new Material("product normal", 5.0, TaxBasicSales.NORMAL.rate, TaxImportDuty.IMPORT.rate);
    private Material materialExemptImport = new Material("product exempt", 7.0, TaxBasicSales.EXEMPT.rate, TaxImportDuty.IMPORT.rate);

    private void assertSalesTaxAndTotal(double salesTax, double total) {
        assertEquals(salesTax, receipt.getTotalSalesTax(), EPSILON);
        assertEquals(total, receipt.getTotalValue(), EPSILON);
    }

    @Before
    public void setUp() throws Exception {
        receipt = new ShoppingCart(Currency.GBP);
    }

    @Test
    public void salesTaxRoundToNearest5c() throws Exception {
        assertEquals(0, SalesTax.roundAmountTo5c(0.0), EPSILON);
        assertEquals(0.95, SalesTax.roundAmountTo5c(0.93), EPSILON);
        assertEquals(0.8, SalesTax.roundAmountTo5c(0.81), EPSILON);
        assertEquals(0.65, SalesTax.roundAmountTo5c(0.65), EPSILON);
        assertEquals(0.7, SalesTax.roundAmountTo5c(0.68), EPSILON);
        assertEquals(0.65, SalesTax.roundAmountTo5c(0.67), EPSILON);
    }

    @Test
    public void emptyReceipt_SalesTax0_Total0() throws Exception {
        assertSalesTaxAndTotal(0.0, 0.0);
    }

    @Test
    public void oneItemNormalLocalQuantity0Price1_SalesTax0_Total0() throws Exception {
        receipt.add(new SoldItem(materialNormalLocal, 0, 1.0));
        assertSalesTaxAndTotal(0.0, 0.0);
    }

    @Test
    public void oneItemNormalLocalQuantity2Price0_SalesTax0_Total0() throws Exception {
        receipt.add(new SoldItem(materialNormalLocal, 2, 0.0));
        assertSalesTaxAndTotal(0.0, 0.0);
    }

    @Test
    public void oneItemNormalLocalQuantity1Price2_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialNormalLocal, 3, materialNormalLocal.getBasePrice()));
        assertSalesTaxAndTotal(0.6, 6.6);
    }

    @Test
    public void oneItemExemptLocal_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialExemptLocal, 2, 3.0));
        assertSalesTaxAndTotal(0.0, 6.0);
    }

    @Test
    public void oneItemExemptImport_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialExemptImport, 2, 3.0));
        assertSalesTaxAndTotal(0.3, 6.3);
    }

    @Test
    public void oneItemNormalImport_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialNormalImport, 2, 3.0));
        assertSalesTaxAndTotal(0.9, 6.9);
    }

    @Test
    public void twoNormalLocalItems_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialNormalLocal, 2, 3.0));
        receipt.add(new SoldItem(materialNormalLocal, 5, 7.0));
        assertSalesTaxAndTotal(4.1, 45.1);
    }

    @Test
    public void twoNormalImportItems_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialNormalImport, 2, 3.0));
        receipt.add(new SoldItem(materialNormalImport, 5, 7.0));
        assertSalesTaxAndTotal(6.15, 47.15);
    }

    @Test
    public void twoExemptLocalItems_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialExemptLocal, 2, 3.0));
        receipt.add(new SoldItem(materialExemptLocal, 5, 7.0));
        assertSalesTaxAndTotal(0.0, 41.0);
    }

    @Test
    public void twoLocalItems_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialNormalLocal, 2, 3.0));
        receipt.add(new SoldItem(materialExemptLocal, 5, 7.0));
        assertSalesTaxAndTotal(0.6, 41.6);
    }

    @Test
    public void twoImportItems_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialNormalImport, 2, 3.0));
        receipt.add(new SoldItem(materialExemptImport, 5, 7.0));
        assertSalesTaxAndTotal(2.65, 43.65);
    }

    @Test
    public void twoExemptItems_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialExemptLocal, 2, 3.0));
        receipt.add(new SoldItem(materialExemptImport, 5, 7.0));
        assertSalesTaxAndTotal(1.75, 42.75);
    }

    @Test
    public void twoNormalItems_SalesTaxTotal() throws Exception {
        receipt.add(new SoldItem(materialNormalLocal, 2, 3.0));
        receipt.add(new SoldItem(materialNormalImport, 5, 7.0));
        assertSalesTaxAndTotal(5.85, 46.85);
    }

    // Scenario 1
    @Test
    public void bookMusicChoco_SalesTaxTotal() throws Exception {
        final Material material1 = new Material("book", 12.49, TaxBasicSales.EXEMPT.rate, TaxImportDuty.LOCAL.rate);
        final Material material2 = new Material("music CD", 14.99, TaxBasicSales.NORMAL.rate, TaxImportDuty.LOCAL.rate);
        final Material material3 = new Material("chocolate bar", 0.85, TaxBasicSales.EXEMPT.rate, TaxImportDuty.LOCAL.rate);
        receipt.add(new SoldItem(material1, 1, material1.getBasePrice()));
        receipt.add(new SoldItem(material2, 1, material2.getBasePrice()));
        receipt.add(new SoldItem(material3, 1, material3.getBasePrice()));
        assertSalesTaxAndTotal(1.50, 29.83);
    }

    // Scenario 2
    @Test
    public void bookImportedChocoImportedPerfume_SalesTaxTotal() throws Exception {
        final Material material1 = new Material("box of chocolates", 10.00, TaxBasicSales.EXEMPT.rate, TaxImportDuty.IMPORT.rate);
        final Material material2 = new Material("bottle of perfume", 47.50, TaxBasicSales.NORMAL.rate, TaxImportDuty.IMPORT.rate);
        receipt.add(new SoldItem(material1, 1, material1.getBasePrice()));
        receipt.add(new SoldItem(material2, 1, material2.getBasePrice()));
        assertSalesTaxAndTotal(7.65, 65.15);
    }

    // Scenario 3
    @Test
    public void fourItemsMixed_SalesTaxTotal() throws Exception {
        final Material material1 = new Material("bottle of perfume", 27.99, TaxBasicSales.NORMAL.rate, TaxImportDuty.IMPORT.rate);
        final Material material2 = new Material("bottle of perfume", 18.99, TaxBasicSales.NORMAL.rate, TaxImportDuty.LOCAL.rate);
        final Material material3 = new Material("packet of headache pills", 9.75, TaxBasicSales.EXEMPT.rate, TaxImportDuty.LOCAL.rate);
        final Material material4 = new Material("box of chocolates", 11.25, TaxBasicSales.EXEMPT.rate, TaxImportDuty.IMPORT.rate);
        receipt.add(new SoldItem(material1, 1, material1.getBasePrice()));
        receipt.add(new SoldItem(material2, 1, material2.getBasePrice()));
        receipt.add(new SoldItem(material3, 1, material3.getBasePrice()));
        receipt.add(new SoldItem(material4, 1, material4.getBasePrice()));
        assertSalesTaxAndTotal(6.65, 74.63);

        assertEquals("1 imported bottle of perfume: £32.19\n" 
                + "1 bottle of perfume: £20.89\n"
                + "1 packet of headache pills: £9.75\n" 
                + "1 imported box of chocolates: £11.80\n"
                + "Sales Taxes: £6.65\n" + "Total: £74.63", receipt.makeReceipt());
    }

    @Test
    public void receiptEmpty() throws Exception {
        assertEquals("Sales Taxes: £0.00\n" + "Total: £0.00", receipt.makeReceipt());
    }

    @Test
    public void receiptOneNormalImportedItem() throws Exception {
        receipt.add(new SoldItem(materialNormalImport, 2, 3.0));
        assertEquals("2 imported product normal: £6.90\n" 
                + "Sales Taxes: £0.90\n" + "Total: £6.90", receipt.makeReceipt());
    }

    @Test
    public void currencyValueFormatGBP() throws Exception {
        NumberFormat form = Currency.GBP.currencyFormat;
        double myNumber = 1234.56d;
        assertEquals("£1,234.56", form.format(myNumber));
    }

}
