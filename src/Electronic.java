public class Electronic extends Product {
    private String brand;
    private int warrantyMonths;

    public Electronic(String name, double price, String description, String color, String brand, int warrantyMonths) {
        super(name, price, description, color);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String toString() {
        return super.toString() + " [бренд: " + brand + ", гарантия: " + warrantyMonths + " мес.]";
    }
}