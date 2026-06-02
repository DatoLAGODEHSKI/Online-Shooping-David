import java.util.ArrayList;
import java.util.List;

public abstract class Category implements Comparable<Category> {
    private static int totalCategories = 0;
    private static int totalSubCategories = 0;

    protected int id;
    protected String title;
    protected String description;
    protected List<Product> products;

    public Category(String title, String description) {
        this.id = ++totalCategories;
        this.title = title;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        products.add(p);
        totalSubCategories++;
    }

    public String getTitle() { return title; }
    public List<Product> getProducts() { return products; }

    public static int getTotalCategories() { return totalCategories; }
    public static int getTotalSubCategories() { return totalSubCategories; }

    public abstract void showInfo();

    @Override
    public int compareTo(Category other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return "Категория #" + id + ": " + title + " - " + description + " (товаров: " + products.size() + ")";
    }
}