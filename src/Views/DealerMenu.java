package Views;

import Controllers.MenuController;
import Services.CarLotService;
import Services.ValidatorService;

/**
 * @author Ian Frye
 *
 * This holds the view for the Dealer Menu
 * Implements the Menu class
 */
public class DealerMenu implements Menu {
    private final CarLotService carService;
    private final MenuController menuController;

    public DealerMenu(CarLotService carService, MenuController menuController) {
        this.carService = carService;
        this.menuController = menuController;
    }

    @Override
    public void displayMenu() {
        System.out.println("\n****** DEALER MENU ******");
        System.out.println("1. Add a Car");
        System.out.println("2. Remove a Car");
        System.out.println("3. Display all cars");
        System.out.println("4. Display average miles");
        System.out.println("5. Display lowest price");
        System.out.println("0. Previous Menu");

        int choice = ValidatorService.getValidNumber("Enter your choice: ", 0, 5, Integer.class);

        handleChoice(choice);

    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1 -> carService.addNewCarToLot();
            case 2 -> carService.removeCarFromLot();
            case 3 -> carService.displayAllCars();
            case 4 -> carService.displayAverageMiles();
            case 5 -> carService.displayLowestPrice();
            case 0 -> menuController.setMenu(MenuChoices.MAIN_MENU);
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }
}
