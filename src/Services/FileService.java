package Services;

import Models.Car;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class FileService {

  public static void saveCarsToFile(String filename, List<Car> cars) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      for (Car car : cars) {
        writer.write(car.toCSV());
        writer.newLine();
      }
    } catch (IOException e) {
      System.out.println("Error saving cars: " + e.getMessage());
    }
  }

  public static void loadCarsFromFile(String filename, List<Car> cars) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        cars.add(Car.fromCSV(line));
      }
    } catch (IOException e) {
      System.out.println("No saved cars found.");
    }
  }

}

