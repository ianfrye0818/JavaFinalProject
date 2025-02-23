package Models;

import Utils.MoneyFormatter;

/**
 *
 * @author Ian Frye
 * This class holds the state and methods for the Car. It could be in a list of cars, or the Car a user wants to
 * purchase.
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

    /**
     * One thing that was annoying during development was having to create new cars each time I ran the program.
     * I decided to save both the car lot's car list and the Customer's list to a csv file so that the state
     * would persist. This helps me in development but also delivers a better UX.
     * @return a CSV representation of the object.
     */

    public String toCSV() {
        return String.format("%s,%s,%s,%d,%d,%.2f", vin, make, model, year, mileage, price);
    }

    /**
     * This will process the CSV line that is passed in and create a new object from it.
     * @param csvLine The line that is being processed.
     * @return A new object from the CSV text
     */
    public static Car fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Car(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4]), Float.parseFloat(parts[5]));
    }

    /**
     * Here we override the .toString() method to better format the car. This is formatted to be used with the headers
     * That are created in a display cars list.
     * @return a formatted string representation of the car obj
     */
    @Override
    public String toString() {
        return String.format("%-10s %-12s %-12s %-6d %-10d %s",
                this.vin, this.make, this.model, this.year, this.mileage, MoneyFormatter.format(this.price));
    }

    /**
     * One of the things we do a lot in this application is make sure a car is not in a list. Since car is an object
     * (reference) you can not directly compare them. By overriding the .equals() method, I can control that i want to
     * compare the VIN which should be unique for each car.
     * @param obj
     * @return
     */
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
