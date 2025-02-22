package Services;

import Models.Car;
import Utils.MoneyFormatter;

public class OfferService {
  private CustomerPurchaseService puchaseService;

  public OfferService(CustomerPurchaseService puchaseService) {
    this.puchaseService = puchaseService;
  }

  public OfferService() {
  }

  public void setPuchaseService(CustomerPurchaseService puchaseService) {
    this.puchaseService = puchaseService;
  }

  public void displayOfferSummary() {

    // Double check that the car puchased is not null.
    Car potentialPurhcasedCar = this.puchaseService.getPurchase();
    if (potentialPurhcasedCar == null) {
      System.out.println("\nNo car selected.");
      return;
    }

    // Get an offer for the car from the manager :)
    double offer = this.getOfferAmount();

    // Format the currency
    String fromattedCarPrice = MoneyFormatter.format(potentialPurhcasedCar.getPrice());
    String formattedDiscount = MoneyFormatter.format(this.puchaseService.getDiscountAmount());
    String formattedOffer = MoneyFormatter.format(offer);

    System.out.println("\n*****OFFER SUMMARY*****");
    System.out.println("Car: " + potentialPurhcasedCar.toString());
    System.out.println("Car Price: " + fromattedCarPrice);
    System.out.println("Discount Amount: " + formattedDiscount);
    System.out.println("Offer Amount: " + formattedOffer);

  }

  public double getOfferAmount() {
    return this.puchaseService.getPurchase().getPrice() - this.puchaseService.getDiscount().getDiscount();
  }
}
