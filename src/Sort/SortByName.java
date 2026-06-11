package Sort;

import models.Product;

import java.util.Comparator;

public class SortByName implements Comparator<Product> {
    public int compare(Product p1, Product p2) {
        return p1.getTitle().compareTo(p2.getTitle());
    }
}