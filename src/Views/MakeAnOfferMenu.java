package Views;

import Controllers.MenuController;
import Models.Car;
import Services.CustomerPurchaseService;
import Services.OfferService;
import Services.ValidatorService;

/**
 *
 * @author Ian Frye
 */
public class MakeAnOfferMenu implements Menu {

    private final MenuController menuController;
    private final OfferService offerService;
    private final CustomerPurchaseService customerPurchaseService;

    public MakeAnOfferMenu(MenuController menucContrller, OfferService offerService,
            CustomerPurchaseService customerPurchaseService) {
        this.offerService = offerService;
        this.menuController = menucContrller;
        this.customerPurchaseService = customerPurchaseService;
    }

    @Override
    public int displayMenu() {

        System.out.println("\n****** MAKE AN OFFER MENU ******");

        Car car = customerPurchaseService.selectCarPurchase();

        if (car == null) {
            System.out.println("\nNo car selected. Returning to main menu.");
            return 0;
        }

        System.out.println("\nYou have selected the following car: ");
        System.out.println(car);

        System.out.println("\nHow are you liking the car? ");
        System.out.println(" 1. Likes the car");
        System.out.println(" 2. Somewhat likes the car");
        System.out.println(" 3. Not easily swayed");
        System.out.println(" 0. Return to main menu");

        return ValidatorService.getValidNumber("Make a choice", 0, 3, Integer.class);
    }

    @Override
    public void handleChoice(int choice) {

        // Set the discount based on the customer's choice
        customerPurchaseService.handleDiscount(choice);

        // Display the offer summary to the customer
        offerService.displayOfferSummary();

        // Hand off to the buy menu.
        menuController.handleBuyCarMenu();
    }

}
