
import Controllers.MenuController;
import Services.CarLotService;
import Services.CustomerPurchaseService;
import Services.CustomerService;
import Services.OfferService;

/**
 *
 * @author Ian Frye
 * CSC 151 - Java Programming
 * Final Project - Car Lot
 * 02.22.2025
 */

/**
 * ***The program is for a company that sells cars and services cars***
 * ***The program should help keep track of the operations of a car lot for your
 * store manager***
 * ***The company want's to move to and OOP system for all of ti's software***
 * [x] The program should include a car class
 * [x] The user should be able to update the car they choose
 * [x] The program should have error handling
 * [x] The program should have an options menu
 * [x] The car class should have the following properties
 * [x] VIN (string)
 * [x] make (string)
 * [x] model (string)
 * [x] year (int > 1970)
 * [x] mileage (a number > 0)
 * [x] price (float greater than 1000)
 * [x] The program should have a menu class
 * [x] Should have options for different things to do several things and
 * returning to the main menu when done.
 * ***Main Functions for users***
 * [x] The user should have the ability to add a car to an array and set all the
 * values for it
 * [x] The user should have the ability to change any car in the array in some
 * way.
 * [x] The user should be able to see a display of all the data of the car the
 * user chooses
 * [x] The user should have an option to display all the cars they have in their
 * array
 * [x] The user should be able to see the average miles for all the cars on the
 * lot
 * [x] The user should be able to view what the lowest price is for all the cars
 * on the lot
 * [x] The use should be able to make an offer for a car
 * [] This should show the customer the list price a discount price and the
 * asking price
 * [x] It should ask the user how they feel about the user
 * [x] Likes the car
 * [x] Somewhat likes the car
 * [x] Not easily swayed
 * [x] Customers who like the car should get a $100 discount
 * [x Customers who somewhat like the car get a $200 discount
 * [x] Customers who are not easily swayed get a $500 discount
 * [x] The program should have at least one array
 * [x] The array should only allow a maximum of 20 cars on the lot
 * [x] The program must have multiple methods
 * [] The output statements should show formatting to two decimal places when
 * printing a price
 * [x] At least one method should incorporate a loop
 * [x] At least one method should should have an If statement
 */
public class Main {
    public static void main(String[] args) {
        // Initialize all of our objects for Dependency Injection.
        CarLotService carLotService = new CarLotService();
        CustomerService customerService = new CustomerService(carLotService);
        CustomerPurchaseService customerPurchaseService = new CustomerPurchaseService(customerService, carLotService);
        OfferService offerService = new OfferService(customerPurchaseService);

        MenuController menuController = new MenuController(carLotService, customerService, offerService,
                customerPurchaseService);

        menuController.displayMainMenu();
    }
}