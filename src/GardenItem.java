public class GardenItem extends Product {
    private String material;

    public GardenItem(String name, double price, String description, String color, String material) {
        super(name, price, description, color);
        this.material = material;
    }

    @Override
    public String toString() {
        return super.toString() + " [материал: " + material + "]";
    }
}