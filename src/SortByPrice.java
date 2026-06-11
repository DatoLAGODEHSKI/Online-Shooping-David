import java.util.Comparator;

public class SortByPrice implements Comparator<Product> {
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}