import java.util.ArrayList;

public class Catalog {
    private static Catalog instance;
    private String name;
    private ArrayList<Category> categories;

    private Catalog(String name) {
        this.name = name;
        this.categories = new ArrayList<>();
    }

    public static Catalog getInstance(String name) {
        if (instance == null) {
            instance = new Catalog(name);
        }
        return instance;
    }

    public void addCategory(Category cat) {
        categories.add(cat);
        System.out.println("Категория '" + cat.getTitle() + "' добавлена");
    }

    public void showCategories() {
        System.out.println("\n=== Каталог: " + name + " ===");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i+1) + ". " + categories.get(i).getTitle());
        }
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}