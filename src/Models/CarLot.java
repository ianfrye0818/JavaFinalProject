package Models;

import Services.FileService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ian Frye
 */

public class CarLot {
    private final List<Car> cars;
    public static final int MAX_CARS = 20;
    private static final String FILE_NAME = "carLotCars.txt";

    public CarLot() {
        this.cars = new ArrayList<>();
        FileService.loadCarsFromFile(FILE_NAME, cars);
    }

    public List<Car> getCars() {
        return cars;
    }

    public int addCar(Car car) {
        if (cars.size() >= MAX_CARS) {
            return -1;
        }
        cars.add(car);
        FileService.saveCarsToFile(FILE_NAME, cars);
        return cars.indexOf(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
        FileService.saveCarsToFile(FILE_NAME, cars);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Car car : cars) {
            if (car != null) {
                sb.append(car).append("\n");
            }
        }
        return sb.toString();
    }
}
