package Views;

import Services.CarLotService;
import Services.ValidatorService;

/**
 *
 * @author Ian Frye
 */

public class DealerMenu implements Menu {
    private final CarLotService carService;

    public DealerMenu(CarLotService carService) {
        this.carService = carService;
    }

    @Override
    public int displayMenu() {
        System.out.println("\n****** DEALER MENU ******");
        System.out.println("1. Add a Car");
        System.out.println("2. Remove a Car");
        System.out.println("3. Display all cars");
        System.out.println("4. Display average miles");
        System.out.println("5. Display lowest price");
        System.out.println("0. Previous Menu");

        return ValidatorService.getValidNumber("Enter your choice: ", 0, 5, Integer.class);
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1 -> carService.addNewCarToLot();
            case 2 -> carService.removeCarFromLot();
            case 3 -> carService.displayAllCars();
            case 4 -> carService.displayAverageMiles();
            case 5 -> carService.displayLowestPrice();
            case 0 -> System.out.println("Returning to previous menu...");
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }
}

