package Services;

import Models.Car;
import Models.CarLot;
import Utils.CarLotListener;
import Utils.EventBus;

/**
 *
 * @author Ian Frye
 */

public class CarLotService extends CarLot implements CarLotListener {

  public CarLotService() {
    EventBus.register(this);
  }

  public void addNewCarToLot() {
    System.out.println("\n=== Adding New Car ===");

    String vin = ValidatorService.getValidString("Enter VIN (press e to exit): ");

    if (!validateVin(vin))
      return;

    String make = ValidatorService.getValidString("Enter make: ");
    String model = ValidatorService.getValidString("Enter model: ");
    int year = ValidatorService.getValidNumber("Enter year: ", 1970, 2024, Integer.class);
    int mileage = ValidatorService.getValidNumber("Enter mileage: ", 0, null, Integer.class);
    float price = ValidatorService.getValidNumber("Enter price: ", 1000f, null, Float.class);

    Car newCar = new Car(vin, make, model, year, mileage, price);
    int index = super.addCar(newCar);

    if (index != -1) {
      System.out.println("\nCar added successfully!");
    } else {
      System.out.println("\nCar already exists.");
    }
  }

  public void displayAllCars() {
    CarService.displayCars(super.getCars());
  }

  public int removeCarFromLot(Car carToRemove) {
    System.out.println("\n=== Removing Car ===");
    if (carToRemove != null) {
      super.removeCar(carToRemove);
      System.out.println("\nCar removed successfully!");
      EventBus.notificyCarRemoved(carToRemove);
      return 1;
    } else {
      System.out.println("\nCar not found.");
      return -1;
    }
  }

  public void removeCarFromLot() {
    if (super.getCars().isEmpty()) {
      System.out.println("\nNo cars to remove.");
      return;
    }
    this.displayAllCars();

    String vin = ValidatorService.getValidString("\nEnter VIN of the car to remove: ");

    for (Car car : super.getCars()) {
      if (car != null && car.getVin().equals(vin)) {
        this.removeCarFromLot(car);
        return;
      }
    }
    System.out.println("\nCar not found.");

  }

  public void displayAverageMiles() {
    CarService.displayAverageMiles(super.getCars());
  }

  public void displayLowestPrice() {
    CarService.displayLowestPrice(super.getCars());
  }

  public boolean isCarLotEmpty() {
    return super.getCars().isEmpty();
  }

  public Car findCarByVin(String vin) {
    for (Car car : super.getCars()) {
      if (car != null && car.getVin().equals(vin)) {
        return car;
      }
    }
    return null;
  }

  private boolean validateVin(String vin) {
    if (vin.toLowerCase().equals("e")) {
      return false;
    }
    if (super.getCars().contains(new Car(vin, null, null, 0, 0, 0))) {
      System.out.println("VIN already exists");
      return false;
    }
    return true;
  }

  @Override
  public void onCarRemoved(Car car) {
    super.removeCar(car);
    System.out.println("\nCar removed from lot: " + car.getVin());
  }
}
