import java.util.Comparator;

public class ProductComparator {
    public static Comparator<Product> byPrice = (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice());
    public static Comparator<Product> byName = (p1, p2) -> p1.getName().compareTo(p2.getName());
    public static Comparator<Product> byColor = (p1, p2) -> p1.getColor().compareTo(p2.getColor());
}