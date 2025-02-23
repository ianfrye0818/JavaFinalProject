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
import Views.MenuChoices;

/**
 *
 * @author Ian Frye
 *
 * This class is responsible for switching out he views based on what menu the
 * user is on.
 */
public class MenuController {

    //Create objects for all the menus
    private final MainMenu mainMenu;
    private final DealerMenu dealerMenu;
    private final CustomerMenu customerMenu;
    private final MakeAnOfferMenu makeAnOfferMenu;
    private final BuyCarMenu buyCarMenu;

    public MenuController(CarLotService carService, CustomerService customerService, OfferService offerService,
            CustomerPurchaseService customerPurchaseService) {
        this.mainMenu = new MainMenu(this);
        this.makeAnOfferMenu = new MakeAnOfferMenu(offerService, customerPurchaseService, this);
        this.dealerMenu = new DealerMenu(carService, this);
        this.customerMenu = new CustomerMenu(carService, customerService, this);
        this.buyCarMenu = new BuyCarMenu(customerPurchaseService, this);
    }

    /**
     * The main loop of the program, This keeps the program running unless the
     * user manually exits.
     *
     * @param menu The menu that should be displayed
     */
    public void setMenu(MenuChoices menu) {
        while (true) {
            switch (menu) {
                case MAIN_MENU -> mainMenu.displayMenu();
                case DEALER_MENU -> dealerMenu.displayMenu();
                case CUSTOMER_MENU -> customerMenu.displayMenu();
                case MAKE_AN_OFFER_MENU -> makeAnOfferMenu.displayMenu();
                case BUY_CAR_MENU -> buyCarMenu.displayMenu();
            }
        }
    }

    /**
     * Display the Main Menu at start of application.
     */
    public void start() {
        setMenu(MenuChoices.MAIN_MENU);
    }

}
