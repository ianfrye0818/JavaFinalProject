package Views;

import Controllers.MenuController;
import Services.ValidatorService;

/**
 *
 * @author Ian Frye
 */
public class MainMenu implements Menu {

    private final MenuController menuController;

    public MainMenu(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    public int displayMenu() {
        System.out.println("\n****** MAIN MENU ******");
        System.out.println("Are you a");
        System.out.println("1. Customer");
        System.out.println("2. Car Dealer");
        System.out.println("0. Exit");
        return ValidatorService.getValidNumber("Make a choice: ", 0, 2, Integer.class);
    }

    public void handleChoice(int choice) {
        switch (choice) {
            case 1 -> menuController.handleCustomerMenu();
            case 2 -> menuController.handleDealerMenu();
            case 0 -> System.exit(0);
        }
    }

}

