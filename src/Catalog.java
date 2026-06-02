import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Catalog {
    private static Catalog instance;
    private List<Category> categories;

    private Catalog() {
        categories = new ArrayList<>();
    }

    public static Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        return instance;
    }

    public void addCategory(Category c) {
        categories.add(c);
    }

    public void showCategories() {
        System.out.println("Каталог:");
        for (Category c : categories) {
            System.out.println(c);
        }
        System.out.println("Всего категорий: " + Category.getTotalCategories());
        System.out.println("Всего товаров: " + Category.getTotalSubCategories());
    }

    public void sortCategories() {
        Collections.sort(categories);
    }

    public List<Category> getCategories() {
        return categories;
    }
}