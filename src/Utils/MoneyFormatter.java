package Utils;

import java.text.NumberFormat;

/**
 * @author Ian Frye
 *
 * Small utility class wrapper on the NumberFormat class to handle formatting money into $##.##
 */
public class MoneyFormatter {

  private static final NumberFormat formatter = NumberFormat.getCurrencyInstance();

  public static String format(double money) {
    return formatter.format(money);
  }
}
