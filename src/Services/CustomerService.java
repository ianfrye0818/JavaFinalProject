package Services;

import Models.Car;
import Models.Customer;
import Utils.CarLotListener;
import Utils.EventBus;

/**
 *
 * @author Ian Frye
 *
 *         This class handles all the service methods for the Customer class
 *         It implements the CarLotListener interface to listen for one car is
 *         deleted from the
 *         Carlot's inventory
 */
public class CustomerService extends Customer implements CarLotListener {
  private final CarLotService carLotService;

  public CustomerService(CarLotService carLotService) {
    this.carLotService = carLotService;
    EventBus.register(this);

  }

  /**
   * Handles the logic for removing a car from the user's choices.
   */
  public void removeCarFromCustomer() {
    // Return early if there are no cars in the list
    if (super.getCarChoices().isEmpty()) {
      System.out.println("\nNo cars to remove.");
      return;
    }

    System.out.println("\n=== Removing Car ===");

    // Display the list of cars for the user.
    this.displayCustomerCars();

    // Get the vin the customer wants to remove.
    String vin = ValidatorService.getValidString("\nEnter VIN of the car to remove: ");

    // Find the car and remove it
    for (Car car : super.getCarChoices()) {
      if (car != null && car.getVin().equals(vin)) {
        super.removeCarChoice(car);
        System.out.println("\nCar removed successfully!");
        return;
      }
    }

    System.out.println("\nCar not found.");
  }

  /**
   * Display the list of chosen cars.
   */
  public void displayCustomerCars() {
    CarService.displayCars(super.getCarChoices());
  }

  /**
   * Display the Average miles of the cars in the list
   */
  public void displayAverageMiles() {
    CarService.displayAverageMiles(super.getCarChoices());
  }

  /**
   * Displays the lowest price car in the list
   */
  public void displayLowestPrice() {
    CarService.displayLowestPrice(super.getCarChoices());
  }

  /**
   *
   * @param vin The vin of the car you're looking up
   * @return The car if found, null if not.
   */
  public Car getCarByVin(String vin) {
    return super.getCarChoices().stream().filter(car -> car.getVin().equals(vin)).findFirst().orElse(null);
  }

  /**
   * Add the car to the customer
   */
  public void addNewCarToCustomerChoices() {

    // If the car lot's inventory is empty return early
    if (carLotService.isCarLotEmpty()) {
      System.out.println("\nNo cars are available at the moment");
      return;
    }

    System.out.println("\n=== Adding New Car ===");
    // Display the list for the user
    carLotService.displayAllCars();

    // Get the vin the customer want's to add.
    String vin = ValidatorService.getValidString("Enter the VIN of the car you want to add: (Enter e to exit) ");

    // If the user types "e" then return to the menu
    if (vin.equalsIgnoreCase("e"))
      return;

    // Find the car the user selects.
    Car selectedCar = carLotService.findCarByVin(vin);

    if (selectedCar != null) {

      // Add the car to the choice if it's not nul
      int index = super.addCarChoice(selectedCar);

      // Return -1 if there are any errors so that they calling method can handle it
      // accordingly.
      if (index != -1) {
        System.out.println("\nCar added successfully!");
      } else {
        System.out.println("\nCar already exists.");
      }
    } else {
      System.out.println("\nCar not found.");
    }
  }

  /**
   * This method listens for changes so that when a car is removed from the car
   * lot's inventory it removes it from the
   * customers choices.
   * 
   * @param car the car you want to remove from the list
   *
   */
  @Override
  public void onCarRemoved(Car car) {
    int resp = super.removeCarChoice(car);

    // Print out a message if the car was removed
    if (resp == 0) {
      System.out.println("\nCar removed from customer choices " + car.getVin());
    }
  }

}
