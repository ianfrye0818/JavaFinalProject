package Views;

import Controllers.MenuController;
import Services.CarLotService;
import Services.CustomerService;
import Services.ValidatorService;

/**
 *
 * @author Ian Frye
 */

public class CustomerMenu implements Menu {

    private final CarLotService carService;
    private final MenuController menuController;
    private final CustomerService customerService;

    public CustomerMenu(CarLotService carService, CustomerService customerService, MenuController menuController) {
        this.carService = carService;
        this.customerService = customerService;
        this.menuController = menuController;
    }

    @Override
    public int displayMenu() {
        System.out.println("\n****** CUSTOMER MENU ******");
        System.out.println("1. Add a car to your choices");
        System.out.println("2. Remove a car from your choices");
        System.out.println("3. Make an offer");
        System.out.println("4. View your choices");
        System.out.println("5. View all cars at the lot");
        System.out.println("6. View average miles at the lot");
        System.out.println("7. View average miles from your choices");
        System.out.println("8. View lowest price at the lot");
        System.out.println("9. View lowest price from your choices");
        System.out.println("0. Previous Menu");

        return ValidatorService.getValidNumber("Enter your choice: ", 0, 9, Integer.class);
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1 -> customerService.addNewCarToCustomerChoices();
            case 2 -> customerService.removeCarFromCustomer();
            case 3 -> menuController.handleMakeAnOfferMenu();
            case 4 -> customerService.displayCustomerCars();
            case 5 -> carService.displayAllCars();
            case 6 -> carService.displayAverageMiles();
            case 7 -> customerService.displayAverageMiles();
            case 8 -> carService.displayLowestPrice();
            case 9 -> customerService.displayLowestPrice();
            case 0 -> System.out.println("Returning to previous menu...");
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

}
