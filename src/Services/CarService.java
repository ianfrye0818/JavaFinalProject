package Services;

import Models.Car;
import java.util.List;

/**
 *
 * @author Ian Frye
 * This contains the utility methods that are designed to use with the carlot and customer services.
 */

public class CarService {

  /**
   * Display the average miles
   * @param cars The list of cars to display the average miles for.
   */
  public static void displayAverageMiles(List<Car> cars) {
    System.out.println("\n=== Displaying Average Miles ===");

    if (!cars.isEmpty()) {
      int averageMiles = calculateAverageMiles(cars);
      System.out.printf("Average miles: %d%n", averageMiles);
    } else {
      System.out.println("\nNo cars available to calculate average miles.");
    }
  }

  /**
   *
   * @param cars The list of cars to display the average miles for.
   */
  public static void displayLowestPrice(List<Car> cars) {
    System.out.println("\n=== Displaying Lowest Price ===");

    Car lowestPriceCar = calculateLowestPrice(cars);

    if (lowestPriceCar != null) {
      displayCars(List.of(lowestPriceCar));
    } else {
      System.out.println("\nNo cars available to display lowest price.");
    }
  }

  /**
   *
//   * @param display the list of cars
   */
  public static void displayCars(List<Car> cars) {

    if (cars.isEmpty()) {
      System.out.println("\nNo cars available to display. Add some cars then try again.");
      return;
    }

    //Format header to work with the car' .toString() method.
    System.out.printf("%-10s %-12s %-12s %-6s %-10s %-10s%n",
        "VIN", "Make", "Model", "Year", "Mileage", "Price");
    System.out.println("--------------------------------------------------------------");

    for (Car car : cars) {
      if (car != null) {

        System.out.println(car);
      } else {
        System.out.println("\nNo cars available to display.");
      }
    }
  }

  /**
   *
   * @param cars The list of cars to calculate
   * @return the Car with the lowest price
   */
  private static Car calculateLowestPrice(List<Car> cars) {
    Car lowestPricedCar = null;
    float lowestPrice = Float.MAX_VALUE;

    for (Car car : cars) {
      if (car != null && car.getPrice() < lowestPrice) {
        lowestPricedCar = car;
      }
    }

    return lowestPricedCar;
  }

  /**
   *
   * @param cars cars to calculate
   * @return the average miles of all the cars in the list.
   */
  private static int calculateAverageMiles(List<Car> cars) {
    int totalMiles = 0;
    int carCount = cars.size();

    for (Car car : cars) {
      if (car != null) {
        totalMiles += car.getMileage();
      }
    }

    if (carCount > 0) {
      return (int) Math.floor((double) totalMiles / carCount);
    } else {
      return 0;
    }
  }
}
