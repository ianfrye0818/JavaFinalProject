package Services;

import Models.Car;
import Models.CustomerPurchase;
import Utils.CustomerDiscount;
import Utils.EventBus;

public class CustomerPurchaseService extends CustomerPurchase {

  private CustomerService customerService;
  private CarLotService carLotService;

  public CustomerPurchaseService() {
  }

  public CustomerPurchaseService(CustomerService customerService, CarLotService carLotService) {
    this.customerService = customerService;
    this.carLotService = carLotService;
  }

  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }

  public void setCarLotService(CarLotService carLotService) {
    this.carLotService = carLotService;
  }

  public Car selectCarPurchase() {
    this.customerService.displayCustomerCars();
    String vin = ValidatorService
        .getValidString("\nEnter the vin on the car you'd like to make an offer on? (Press e to exit) ");

    if (vin.toLowerCase().equals("e"))
      return null;
    // check to see if the customer vin in valid
    if (!this.customerService.validateWithVIN(vin)) {
      System.out.println("Invalid VIN");
      return null;
    }
    super.setPurchase(this.customerService.getCarByVin(vin));

    return super.getPurchase();
  }

  public void handlePurchase(String vin) {
    Car car = carLotService.findCarByVin(vin);
    if (car != null) {
      super.setPurchase(car);
      int resp = carLotService.removeCarFromLot(car);
      if (resp == 1) {
        System.out.println("\nCar purchased successfully!");
      } else {
        System.out.println("\nCar not found.");
      }
    } else {
      System.out.println("\nCar not found.");
    }
  }

  public void handleDiscount(int choice) {
    switch (choice) {
      case 1 -> this.setDiscount(CustomerDiscount.LIKES_THE_CAR);
      case 2 -> this.setDiscount(CustomerDiscount.SOMEWHAT_LIKES_THE_CAR);
      case 3 -> this.setDiscount(CustomerDiscount.NOT_EASILY_SWAYED);
      case 0 -> System.out.println("\nReturning to previous menu...");
    }
  }

  public int buyCar() {
    // double check that there is a car to purchase
    if (super.getPurchase() == null) {
      System.out.println("\nNo car to purchase.");
      return -1;
    }
    EventBus.notificyCarRemoved(getPurchase());
    System.out.println("\nCar purchased successfully!");
    System.out
        .println(
            "\nEnjoy your new " + super.getPurchase().getMake() + " " + super.getPurchase().getModel() + "!");

    return 1;

  }

}
