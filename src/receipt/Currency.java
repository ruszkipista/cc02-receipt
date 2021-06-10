package receipt;

import java.text.NumberFormat;
import java.util.Locale;

public enum Currency {
    GBP("British Pound",    NumberFormat.getCurrencyInstance(new Locale("en", "GB"))), 
    EUR("Euro",             NumberFormat.getCurrencyInstance(new Locale("de", "DE"))), 
    HUF("Hungarian Forint", NumberFormat.getCurrencyInstance(new Locale("hu", "HU")));

    public final String description;
    public final NumberFormat currencyFormat;

    private Currency(String description, NumberFormat currencyFormat) {
        this.description = description;
        this.currencyFormat = currencyFormat;
    }

}