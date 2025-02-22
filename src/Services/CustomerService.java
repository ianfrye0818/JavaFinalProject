package Services;

import Models.Car;
import Models.Customer;
import Utils.CarLotListener;
import Utils.CustomerDiscount;
import Utils.EventBus;

/**
 *
 * @author Ian Frye
 */
public class CustomerService extends Customer implements CarLotListener {
  private final CarLotService carLotService;

  public CustomerService(CarLotService carLotService) {
    this.carLotService = carLotService;
    EventBus.regeister(this);
  }

  public void removeCarFromCustomer() {
    if (super.getCarChoices().isEmpty()) {
      System.out.println("\nNo cars to remove.");
      return;
    }
    System.out.println("\n=== Removing Car ===");

    this.displayCustomerCars();

    String vin = ValidatorService.getValidString("\nEnter VIN of the car to remove: ");

    for (Car car : super.getCarChoices()) {
      if (car != null && car.getVin().equals(vin)) {
        super.removeCarChoice(car);
        System.out.println("\nCar removed successfully!");
        return;
      }
    }

    System.out.println("\nCar not found.");
  }

  public void displayCustomerCars() {
    CarService.displayCars(super.getCarChoices());
  }

  public void displayAverageMiles() {
    CarService.displayAverageMiles(super.getCarChoices());
  }

  public void displayLowestPrice() {
    CarService.displayLowestPrice(super.getCarChoices());
  }

  public Car getCarByVin(String vin) {
    return super.getCarChoices().stream().filter(car -> car.getVin().equals(vin)).findFirst().orElse(null);
  }

  public void addNewCarToCustomerChoices() {
    if (carLotService.isCarLotEmpty()) {
      System.out.println("\nNo cars are available at the moment");
      return;
    }

    System.out.println("\n=== Adding New Car ===");
    carLotService.displayAllCars();

    String vin = ValidatorService.getValidString("Enter the VIN of the car you want to add: ");
    Car selectedCar = carLotService.findCarByVin(vin);

    if (selectedCar != null) {
      int index = super.addCarChoice(selectedCar);
      if (index != -1) {
        System.out.println("\nCar added successfully!");
      } else {
        System.out.println("\nCar already exists.");
      }
    } else {
      System.out.println("\nCar not found.");
    }
  }

  @Override
  public void onCarRemoved(Car car) {
    super.removeCarChoice(car);
    System.out.println("\nCar remvoed from customer choices " + car.getVin());
  }

}
