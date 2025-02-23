package Views;

import Controllers.MenuController;
import Services.CustomerPurchaseService;
import Services.ValidatorService;

/**
 * @author Ian Frye
 *
 * This holds the view for the Buy Car Menu
 * Implements the Menu class
 */

public class BuyCarMenu implements Menu {

  private final CustomerPurchaseService customerPurchaseService;
  private final MenuController menuController;

  public BuyCarMenu(CustomerPurchaseService customerPurchaseService, MenuController menuController) {
    this.customerPurchaseService = customerPurchaseService;
    this.menuController = menuController;
  }

  @Override
  public void displayMenu() {
    System.out.println("\n****** BUY CAR MENU ******");
    System.out.println(" 1. Buy a car");
    System.out.println(" 2. Don't buy car");
    System.out.println(" 0. Return to main menu");

    int choice = ValidatorService.getValidNumber("What would you like to do?", 0, 2, Integer.class);
    handleChoice(choice);
  }

  @Override
  public void handleChoice(int choice) {
    switch (choice) {
      case 1:
        this.customerPurchaseService.buyCar();
      case 2:
        this.customerPurchaseService.removePurchase();
        this.menuController.setMenu(MenuChoices.CUSTOMER_MENU);
      default:
        this.menuController.setMenu(MenuChoices.MAIN_MENU);
    }
  }
}
