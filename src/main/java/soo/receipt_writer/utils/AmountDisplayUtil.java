package soo.receipt_writer.utils;

import java.text.NumberFormat;

public class AmountDisplayUtil {

    static public String format(String amount) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        return numberFormat.format(Long.parseLong(amount));
    }

    static public String format(Integer amount) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        return numberFormat.format(amount);
    }

    static public String format(Long amount) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        return numberFormat.format(amount);
    }
}
