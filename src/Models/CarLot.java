package Models;

import Services.FileService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ian Frye
 * This holds the state for the Dealer's car lot.
 */

public class CarLot {
    private final List<Car> cars;
    public static final int MAX_CARS = 20; //Max of 20 cars allowed at the dealership
    private static final String FILE_NAME = "carLotCars.txt"; //File to save state of the cars.

    public CarLot() {
        this.cars = new ArrayList<>();
        FileService.loadCarsFromFile(FILE_NAME, cars);
    }

    /**
     *
     * @return The cars that are on the lot
     */
    public List<Car> getCars() {
        return cars;
    }

    /**
     *
     * @param car The car you would like to add to the lot
     * @return will return -1 if there was an error - this way the calling method can handle it accordingly.
     */
    public int addCar(Car car) {
        if (cars.size() >= MAX_CARS) {
            return -1;
        }
        cars.add(car);

        //Save the state to the file once we have added the car.
        FileService.saveCarsToFile(FILE_NAME, cars);
        return cars.indexOf(car);
    }

    /**
     *
     * @param car The car you want to remove from the list.
     */
    public void removeCar(Car car) {
        cars.remove(car);

        //Save the state to the file once we remove the car from the list.
        FileService.saveCarsToFile(FILE_NAME, cars);
    }

    /**
     *
     * @return A string representation of the car lot.
     */
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
