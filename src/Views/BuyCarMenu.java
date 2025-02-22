package Views;

import Controllers.MenuController;
import Services.CustomerPurchaseService;
import Services.ValidatorService;

public class BuyCarMenu implements Menu {

  private CustomerPurchaseService customerPurchaseService;
  private MenuController menuController;

  public BuyCarMenu() {
  }

  public void setCustomerPurchaseService(CustomerPurchaseService customerPurchaseService) {
    this.customerPurchaseService = customerPurchaseService;
  }

  public void setMenuController(MenuController menuController) {
    this.menuController = menuController;
  }

  @Override
  public int displayMenu() {
    System.out.println("\n****** BUY CAR MENU ******");
    System.out.println(" 1. Buy a car");
    System.out.println(" 2. Don't buy car");
    System.out.println(" 0. Return to main menu");

    return ValidatorService.getValidNumber("What would you like to do?", 0, 2, Integer.class);
  }

  @Override
  public void handleChoice(int choice) {
    switch (choice) {
      case 1 -> customerPurchaseService.buyCar();
      case 2 -> customerPurchaseService.removePurchase();
      case 0 -> System.out.println("Returning to main menu");
    }
  }
}
