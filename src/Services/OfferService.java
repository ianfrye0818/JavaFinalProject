package Services;

import Models.Car;
import Utils.MoneyFormatter;

/**
 * @author Ian Frye
 * This class contains the service methods for handling an offer for the customer
 */

public class OfferService {
  private final CustomerPurchaseService purchaseService;

  public OfferService(CustomerPurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  /**
   * Display the offer to the user in a formatted manner
   */
  public void displayOfferSummary() {

    // Double check that the car purchased is not null.
    Car potentialPurhcasedCar = this.purchaseService.getPurchase();
    if (potentialPurhcasedCar == null) {
      System.out.println("\nNo car selected.");
      return;
    }

    // Get an offer for the car from the manager :)
    double offer = this.getOfferAmount();

    // Format the currency
    String formattedCarPrice = MoneyFormatter.format(potentialPurhcasedCar.getPrice());
    String formattedDiscount = MoneyFormatter.format(this.purchaseService.getDiscountAmount());
    String formattedOffer = MoneyFormatter.format(offer);

    System.out.println("\n*****OFFER SUMMARY*****");
    System.out.println("Car: " + potentialPurhcasedCar);
    System.out.println("Car Price: " + formattedCarPrice);
    System.out.println("Discount Amount: " + formattedDiscount);
    System.out.println("Offer Amount: " + formattedOffer);

  }

  /**
   * Calculate the offer.
   * @return The offer for the user
   */
  public double getOfferAmount() {
    return this.purchaseService.getPurchase().getPrice() - this.purchaseService.getDiscount().getDiscount();
  }
}
