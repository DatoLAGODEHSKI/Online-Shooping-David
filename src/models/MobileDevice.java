package models;

public class MobileDevice extends Electronic {
    public int batteryCapacity;
    double screenSize;

    public MobileDevice(String title, double price, String description, String brand,
                        int warrantyMonths, int batteryCapacity, double screenSize) {
        super(title, price, description, brand, warrantyMonths);
        this.batteryCapacity = batteryCapacity;
        this.screenSize = screenSize;
    }
}