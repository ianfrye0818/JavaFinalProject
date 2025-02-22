package Models;

import Utils.MoneyFormatter;

/**
 *
 * @author Ian Frye
 */

public class Car {
    private final String vin;
    private final String make;
    private final String model;
    private final int year;
    private final int mileage;
    private final float price;

    public Car(String vin, String make, String model, int year, int mileage, float price) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    public String getVin() {
        return vin;
    }

    public int getMileage() {
        return mileage;
    }

    public float getPrice() {
        return price;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%d,%d,%.2f", vin, make, model, year, mileage, price);
    }

    public static Car fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Car(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4]), Float.parseFloat(parts[5]));
    }

    @Override
    public String toString() {
        return String.format("%-10s %-12s %-12s %-6d %-10d %s",
                this.vin, this.make, this.model, this.year, this.mileage, MoneyFormatter.format(this.price));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Car car = (Car) obj;

        return vin.equals(car.vin); // Compare vin since it should be unique.
    }

}
