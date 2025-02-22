package Utils;

import java.util.ArrayList;
import java.util.List;
import Models.Car;

public class EventBus {
  private static final List<CarLotListener> listeners = new ArrayList<>();

  public static void register(CarLotListener listener) {
    listeners.add(listener);
  }

  public static void notificyCarRemoved(Car car) {
    for (CarLotListener listener : listeners) {
      listener.onCarRemoved(car);
    }
  }
}
