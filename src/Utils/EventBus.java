package Utils;

import java.util.ArrayList;
import java.util.List;
import Models.Car;

/**
 * @author Ian Frye
 * This class creates a global state event listener that alows us to register listensers and remove cars from lists
 * based on when a car is removed from the Car lot or customer's choice car lists.
 */
public class EventBus {
  private static final List<CarLotListener> listeners = new ArrayList<>();

  public static void register(CarLotListener listener) {
    listeners.add(listener);
  }

  public static void notifyCarRemoved(Car car) {
    for (CarLotListener listener : listeners) {
      listener.onCarRemoved(car);
    }
  }
}
