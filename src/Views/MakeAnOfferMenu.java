package Views;

import Controllers.MenuController;
import Models.Car;
import Services.CustomerPurchaseService;
import Services.OfferService;
import Services.ValidatorService;

/**
 * @author Ian Frye
 *
 *         This holds the view for the Make an Offer Menu
 *         Implements the Menu class
 */
public class MakeAnOfferMenu implements Menu {

    private final MenuController menuController;
    private final OfferService offerService;
    private final CustomerPurchaseService customerPurchaseService;

    public MakeAnOfferMenu(OfferService offerService,
            CustomerPurchaseService customerPurchaseService, MenuController menuController) {
        this.offerService = offerService;
        this.menuController = menuController;
        this.customerPurchaseService = customerPurchaseService;
    }

    @Override
    public void displayMenu() {

        System.out.println("\n****** MAKE AN OFFER MENU ******");

        Car car = customerPurchaseService.selectCarPurchase();

        if (car == null) {
            menuController.setMenu(MenuChoices.CUSTOMER_MENU);
        }

        System.out.println("\nYou have selected the following car:\n");
        System.out.println(car);

        System.out.println("\nHow are you liking the car? ");
        System.out.println(" 1. I like the car");
        System.out.println(" 2. I somewhat like the car");
        System.out.println(" 3. I'm not easily swayed");
        System.out.println(" 0. Return to previous menu");

        int choice = ValidatorService.getValidNumber("Make a choice", 0, 3, Integer.class);
        handleChoice(choice);
    }

    @Override
    public void handleChoice(int choice) {
        if (choice == 0)
            this.menuController.setMenu(MenuChoices.CUSTOMER_MENU);

        // Set the discount based on the customer's choice
        customerPurchaseService.handleDiscount(choice);

        // Display the offer summary to the customer
        offerService.displayOfferSummary();

        // Hand off to the buy menu.
        menuController.setMenu(MenuChoices.BUY_CAR_MENU);
    }

}
