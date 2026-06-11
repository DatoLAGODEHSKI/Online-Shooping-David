public class GardenItem extends Product {
    String material;

    public GardenItem(String title, double price, String description, String material) {
        super(title, price, description);
        this.material = material;
    }
}