package models;

public class ProductFactory {

    public static Product createElectronic(String title, double price, String description,
                                           String brand, int warranty) {
        return new Electronic(title, price, description, brand, warranty);
    }

    public static Product createMobileDevice(String title, double price, String description,
                                             String brand, int warranty, int battery, double screen) {
        return new MobileDevice(title, price, description, brand, warranty, battery, screen);
    }

    public static Product createGardenItem(String title, double price, String description, String material) {
        return new GardenItem(title, price, description, material);
    }
}