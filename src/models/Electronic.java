package models;

public class Electronic extends Product {
    public String brand;
    int warrantyMonths;

    public Electronic(String title, double price, String description, String brand, int warrantyMonths) {
        super(title, price, description);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }
}