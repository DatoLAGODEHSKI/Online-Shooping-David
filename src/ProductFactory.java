public class ProductFactory {
    public static Product createProduct(String type, String name, double price, String description, String color, Object... extra) {
        switch (type) {
            case "mobile":
                return new MobileDevice(name, price, description, color,
                        (String) extra[0], (int) extra[1], (double) extra[2]);
            case "electronic":
                return new Electronic(name, price, description, color,
                        (String) extra[0], (int) extra[1]);
            case "garden":
                return new GardenItem(name, price, description, color, (String) extra[0]);
            default:
                throw new IllegalArgumentException("Неизвестный тип");
        }
    }
}