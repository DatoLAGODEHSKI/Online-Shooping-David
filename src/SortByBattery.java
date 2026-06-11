import java.util.Comparator;

public class SortByBattery implements Comparator<Product> {
    public int compare(Product p1, Product p2) {
        int b1 = 0, b2 = 0;
        if (p1 instanceof MobileDevice) {
            b1 = ((MobileDevice) p1).batteryCapacity;
        }
        if (p2 instanceof MobileDevice) {
            b2 = ((MobileDevice) p2).batteryCapacity;
        }
        return Integer.compare(b2, b1);
    }
}