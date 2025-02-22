package Utils;

import java.text.NumberFormat;

public class MoneyFormatter {

  private static final NumberFormat formatter = NumberFormat.getCurrencyInstance();

  public static String format(double money) {
    return formatter.format(money);
  }
}
