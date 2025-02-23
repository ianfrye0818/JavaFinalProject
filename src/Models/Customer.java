package Models;

import Services.FileService;
import java.util.ArrayList;

/**
 *
 * @author Ian Frye\
 *
 *         This holds the state and properties for a customer looking to puchase
 *         a car.
 */

public class Customer {

    // Users can add an array of choices to be able to select from.
    private final ArrayList<Car> carChoices;

    // Each time a user makes a change to their choices. their state is updated in
    // this file.
    private static final String FILE_NAME = "customerCars.txt";

    public Customer() {
        carChoices = new ArrayList<>();
        FileService.loadCarsFromFile(FILE_NAME, carChoices);
    }

    /**
     *
     * @return A list of the current cars in the customer's choices.
     */
    public ArrayList<Car> getCarChoices() {
        return carChoices;
    }

    /**
     *
     * @param car The car the customer want's to add
     * @return -1 if there was an error so the calling method can handle it
     *         accordingly.
     */
    public int addCarChoice(Car car) {
        if (carChoices.contains(car)) {
            return -1;
        }
        carChoices.add(car);

        // Save the state to a file so that the user's choices are not lost between
        // program loads.
        FileService.saveCarsToFile(FILE_NAME, carChoices);
        return carChoices.indexOf(car);
    }

    /**
     *
     * @param car The car the customer wants to remove from their choices.
     */
    public int removeCarChoice(Car car) {
        if (!carChoices.contains(car)) {
            return -1;
        }

        carChoices.remove(car);

        // Save the state to a file so that the user's choices are not lost between
        // program loads.
        FileService.saveCarsToFile(FILE_NAME, carChoices);

        return 0;
    }

    /**
     *
     * @param vin The vin of the car being validated
     * @return Whether the VIN entered matches any of the cars in the users choices.
     */
    public boolean validateWithVIN(String vin) {
        return carChoices.stream().anyMatch(car -> car.getVin().equals(vin));
    }
}
