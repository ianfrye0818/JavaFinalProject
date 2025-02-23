package Models;

import Utils.CustomerDiscount;

/**
 *
 * @author Ian Frye\
 *
 * This holds the state and properties for a Purhcase made by the customer.
 */

public class CustomerPurchase {
  private Car purchase = null;
  private CustomerDiscount discount = CustomerDiscount.NONE; // Discount based on how the customer is feeling  ->
  // start with a $0 discount

  /**
   *
   * @return The current car that is being purchased (if there is one).
   */
  public Car getPurchase() {
    return this.purchase;
  }

  /**
   *
   * @param purchase The car the user is looking to purchase.
   */
  public void setPurchase(Car purchase) {
    this.purchase = purchase;
  }

  /**
   *
   * @param discount The discount the user will be getting based on how they are feeling.
   */
  public void setDiscount(CustomerDiscount discount) {
    this.discount = discount;
  }

  /**
   * @return what the customer's current discount is.
   */
  public CustomerDiscount getDiscount() {
    return this.discount;
  }

  /**
   *
   * @return The dollar amount associated with the customer's discount
   */
  public double getDiscountAmount() {
    return this.discount.getDiscount();
  }

  /**
   * Remove the customer's purchase if the no longer wish to purchase the car.
   */
  public void removePurchase() {
    this.setPurchase(null);
  }
}
