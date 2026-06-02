public class MobileDevice extends Electronic {
    private double screenSize;

    public MobileDevice(String name, double price, String description, String color, String brand, int warrantyMonths, double screenSize) {
        super(name, price, description, color, brand, warrantyMonths);
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return super.toString() + " | Экран: " + screenSize + "\"";
    }
}