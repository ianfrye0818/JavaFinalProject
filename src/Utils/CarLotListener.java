package Utils;

import Models.Car;

/**
 * @author Ian Frye
 *
 * This interface declares an onCarRemoved method that listens for changes in the EventBus to handle when a car is
 * removed from the carlot.
 */
public interface CarLotListener {
  void onCarRemoved(Car car);
}
