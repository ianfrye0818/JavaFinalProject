package Views;

import Controllers.MenuController;
import Services.ValidatorService;

/**
 * @author Ian Frye
 *
 * This holds the view for the Main Menu
 * Implements the Menu class
 */
public class MainMenu implements Menu {

    private final MenuController menuController;

    public MainMenu(MenuController menuController) {
        this.menuController = menuController;
    }

    public void displayMenu() {
        System.out.println("\n****** MAIN MENU ******");
        System.out.println("Are you a");
        System.out.println("1. Customer");
        System.out.println("2. Car Dealer");
        System.out.println("0. Exit");
        int choice = ValidatorService.getValidNumber("Make a choice: ", 0, 2, Integer.class);
        handleChoice(choice);
    }

    public void handleChoice(int choice) {
        switch (choice) {
            case 1 -> menuController.setMenu(MenuChoices.CUSTOMER_MENU);
            case 2 -> menuController.setMenu(MenuChoices.DEALER_MENU);
            case 0 -> System.exit(0);
        }
    }

}
