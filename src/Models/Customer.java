package Models;

import Services.FileService;
import java.util.ArrayList;

/**
 *
 * @author Ian Frye
 */

public class Customer {

    private final ArrayList<Car> carChoices;
    private static final String FILE_NAME = "customerCars.txt";

    public Customer() {
        carChoices = new ArrayList<>();
        FileService.loadCarsFromFile(FILE_NAME, carChoices);
    }

    public ArrayList<Car> getCarChoices() {
        return carChoices;
    }

    public int addCarChoice(Car car) {
        if (carChoices.contains(car)) {
            return -1;
        }
        carChoices.add(car);
        FileService.saveCarsToFile(FILE_NAME, carChoices);
        return carChoices.indexOf(car);
    }

    public void removeCarChoice(Car car) {
        if (!carChoices.contains(car)) {
            System.out.println("Car not found.");
            return;
        }
        carChoices.remove(car);
        FileService.saveCarsToFile(FILE_NAME, carChoices);
    }

    public boolean validateWithVIN(String vin) {
        return carChoices.stream().anyMatch(car -> car.getVin().equals(vin));
    }
}
