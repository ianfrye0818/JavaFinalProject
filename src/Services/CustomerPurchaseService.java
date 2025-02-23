package Services;

import Models.Car;
import Models.CustomerPurchase;
import Utils.CustomerDiscount;
import Utils.EventBus;

/**
 * @author Ian Frye
 * Contains all the service methods for the CustomerPurchase class
 */
public class CustomerPurchaseService extends CustomerPurchase {

  private final CustomerService customerService;

    public CustomerPurchaseService(CustomerService customerService) {
    this.customerService = customerService;
    }

  /**
   *
   * @return The car the user would like to purchase
   */
  public Car selectCarPurchase() {
    this.customerService.displayCustomerCars();
    String vin = ValidatorService
        .getValidString("\nEnter the vin on the car you'd like to make an offer on? (Press e to exit) ");

    if (vin.equalsIgnoreCase("e"))
      return null;
    // check to see if the customer vin in valid
    if (!this.customerService.validateWithVIN(vin)) {
      System.out.println("Invalid VIN");
      return null;
    }

    super.setPurchase(this.customerService.getCarByVin(vin));

    return super.getPurchase();
  }

  /**
   *
   * Set's the discount for the customer based on their choice.
   * @param choice the Choice of the user
   *
   */
  public void handleDiscount(int choice) {
    switch (choice) {
      case 1 -> this.setDiscount(CustomerDiscount.LIKES_THE_CAR);
      case 2 -> this.setDiscount(CustomerDiscount.SOMEWHAT_LIKES_THE_CAR);
      case 3 -> this.setDiscount(CustomerDiscount.NOT_EASILY_SWAYED);
      case 0 -> System.out.println("\nReturning to previous menu...");
    }
  }

  /**
   * Handles the logic for buying the car.
   */
  public void buyCar() {
    // double check that there is a car to purchase
    if (super.getPurchase() == null) {
      System.out.println("\nNo car to purchase.");
      return;
    }

    //Notify the listeners to remove the cars from their list.
    EventBus.notifyCarRemoved(getPurchase());
    System.out.println("\nCar purchased successfully!");
    System.out
        .println(
            "\nEnjoy your new " + super.getPurchase().getMake() + " " + super.getPurchase().getModel() + "!");

  }

}
