package Services;

import Models.Car;
import Models.CarLot;
import Utils.CarLotListener;
import Utils.EventBus;

/**
 *
 * @author Ian Frye
 * <p>
 * This class contains the methods that direcly perform actions on the Carlot
 * It implements the CarLotListener class which is an event loop that will delete the car from the carlot inventory
 * if the user decides to buy the car.
 */

public class CarLotService extends CarLot implements CarLotListener {

  public CarLotService() {
    EventBus.register(this); // register the event with the listener
  }

  /**
   * Add a car to the lot.
   */
  public void addNewCarToLot() {
    System.out.println("\n=== Adding New Car ===");

    String vin = ValidatorService.getValidString("Enter VIN (press e to exit): ");

    // If the user enters e - return to the menu
    if (vin.equalsIgnoreCase("e")) {
      return;
    }
    // Validate that the string entered is not empty and that it's not an 'e'
    if (!validateVin(vin))
      return;

    //Enter the rest of the info if the VIN is valid.
    String make = ValidatorService.getValidString("Enter make: ");
    String model = ValidatorService.getValidString("Enter model: ");
    int year = ValidatorService.getValidNumber("Enter year: ", 1970, 2024, Integer.class);
    int mileage = ValidatorService.getValidNumber("Enter mileage: ", 0, null, Integer.class);
    float price = ValidatorService.getValidNumber("Enter price: ", 1000f, null, Float.class);

    //Create a car object with the user's input.
    Car newCar = new Car(vin, make, model, year, mileage, price);

    //Add the car to the inventory
    int index = super.addCar(newCar);

    //Catch any errors
    if (index != -1) {
      System.out.println("\nCar added successfully!");
    } else {
      System.out.println("\nCar already exists.");
    }
  }

  /**
   * Display the cars currently in the inventory
   */
  public void displayAllCars() {
    CarService.displayCars(super.getCars());
  }

  /**
   * @param carToRemove the car that the user will be removing from the lot
   */
  public void removeCarFromLot(Car carToRemove) {
    System.out.println("\n=== Removing Car ===");

    //Check to make sure the car exists.
    if (carToRemove != null) {
      //Let the event handler know to notify the listeners that the car has been removed
      //This will remove the car from the user's inventory.
      EventBus.notifyCarRemoved(carToRemove);
    } else {
      System.out.println("\nCar not found.");
    }
  }

  /**
   * This is an overloaded method of previous. It adds the edition of displaying a list of cars so the user can pick
   * on which car they want to remove.
   */
  public void removeCarFromLot() {

    //If there are no cars in the inventory - we can exit early.
    if (super.getCars().isEmpty()) {
      System.out.println("\nNo cars to remove.");
      return;
    }

    //Display the list of cars avail.
    this.displayAllCars();


    //Get the vin of the car they would like to remove.
    String vin = ValidatorService.getValidString("\nEnter VIN of the car to remove: (Enter e to exit) ");

    if (vin.equalsIgnoreCase("e")) return;

    //Loop through the cars and see if the vin entered exists in the list. if it does - call the removeCarFromLot
    // method to finish removing the car.
    for (Car car : super.getCars()) {
      if (car != null && car.getVin().equals(vin)) {
        this.removeCarFromLot(car);
        return;
      }
    }

    System.out.println("\nCar not found.");

  }


  /**
   * Print out the average miles for all the cars on the lot.
   */
  public void displayAverageMiles() {
    CarService.displayAverageMiles(super.getCars());
  }

  /**
   * Print out the lowest priced car on the lot.
   */
  public void displayLowestPrice() {
    CarService.displayLowestPrice(super.getCars());
  }

  /**
   *
   * @return whether the car lot's inventory is empty.
   */
  public boolean isCarLotEmpty() {
    return super.getCars().isEmpty();
  }

  /**
   *
   * @param vin to find in the car list.
   * @return the car that it found or null if there wasn't one.
   */
  public Car findCarByVin(String vin) {
    for (Car car : super.getCars()) {
      if (car != null && car.getVin().equals(vin)) {
        return car;
      }
    }
    return null;
  }


  /**
   *
   * @param vin The vin of the car to validate
   * @return if the car is in the car lots inventory
   */
  private boolean validateVin(String vin) {

    if(findCarByVin(vin) != null ) {
      System.out.println("VIN already exists");
      return false;
    }
    return true;
  }

  /**
   * Listener method for the remove Car events.
   * @param car car to be removed
   */
  @Override
  public void onCarRemoved(Car car) {
    super.removeCar(car);
    System.out.println("\nCar removed from lot: " + car.getVin());
  }
}
