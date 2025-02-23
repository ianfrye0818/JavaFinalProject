
import Controllers.MenuController;
import Services.CarLotService;
import Services.CustomerPurchaseService;
import Services.CustomerService;
import Services.OfferService;

/**
 * @author Ian Frye
 * CSC 151 - Java Programming
 * Final Project - Car Lot
 * 02.22.2025
*/
public class Main {
    public static void main(String[] args) {

        // Initialize all of our objects for Dependency Injection.
        CarLotService carLotService = new CarLotService();
        CustomerService customerService = new CustomerService(carLotService);
        CustomerPurchaseService purchaseService = new CustomerPurchaseService(customerService);
        OfferService offerService = new OfferService(purchaseService);
        MenuController menuController = new MenuController(carLotService, customerService, offerService,
                purchaseService);


        //Display the main menu
        menuController.start();
    }
}