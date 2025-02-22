package Models;

import Utils.CustomerDiscount;

public class CustomerPurchase {
  private Car purchase = null;
  private CustomerDiscount discount = CustomerDiscount.NONE;

  public Car getPurchase() {
    return this.purchase;
  }

  public void setPurchase(Car purchase) {
    this.purchase = purchase;
  }

  public void setDiscount(CustomerDiscount discount) {
    this.discount = discount;
  }

  public CustomerDiscount getDiscount() {
    return this.discount;
  }

  public double getDiscountAmount() {
    return this.discount.getDiscount();
  }

  public void removePurchase() {
    this.setPurchase(null);
  }
}
