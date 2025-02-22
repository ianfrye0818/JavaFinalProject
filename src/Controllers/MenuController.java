package Controllers;

import Services.CarLotService;
import Services.CustomerPurchaseService;
import Services.CustomerService;
import Services.OfferService;
import Views.BuyCarMenu;
import Views.CustomerMenu;
import Views.DealerMenu;
import Views.MainMenu;
import Views.MakeAnOfferMenu;

/**
 *
 * @author Ian Frye
 */
public class MenuController {
    private final MainMenu mainMenu;
    private final DealerMenu dealerMenu;
    private final CustomerMenu customerMenu;
    private final MakeAnOfferMenu makeAnOfferMenu;
    private final BuyCarMenu buyCarMenu;

    public MenuController(CarLotService carService, CustomerService customerService, OfferService offerService,
            CustomerPurchaseService customerPurchaseService) {
        this.mainMenu = new MainMenu(this);
        this.makeAnOfferMenu = new MakeAnOfferMenu(this, offerService, customerPurchaseService);
        this.dealerMenu = new DealerMenu(carService);

        this.customerMenu = new CustomerMenu(carService, customerService, this);

        this.buyCarMenu = new BuyCarMenu();
        this.buyCarMenu.setCustomerPurchaseService(customerPurchaseService);
        this.buyCarMenu.setMenuController(this);
    }

    public void displayMainMenu() {
        while (true) {
            int choice = mainMenu.displayMenu();
            if (choice == 0)
                return;
            mainMenu.handleChoice(choice);
        }
    }

    public void handleDealerMenu() {
        while (true) {
            int choice = dealerMenu.displayMenu();
            if (choice == 0)
                return;
            dealerMenu.handleChoice(choice);
        }
    }

    public void handleCustomerMenu() {
        while (true) {
            int choice = customerMenu.displayMenu();
            if (choice == 0)
                return;
            customerMenu.handleChoice(choice);
        }
    }

    public void handleMakeAnOfferMenu() {
        while (true) {
            int choice = makeAnOfferMenu.displayMenu();
            if (choice == 0)
                return;
            makeAnOfferMenu.handleChoice(choice);
        }
    }

    public void handleBuyCarMenu() {
        while (true) {
            int choice = buyCarMenu.displayMenu();
            if (choice == 0)
                return;
            buyCarMenu.handleChoice(choice);
        }

    }

}
