public class ConcreteCategory extends Category {
    public ConcreteCategory(String title, String description) {
        super(title, description);
    }

    @Override
    public void showInfo() {
        System.out.println("\n=== " + title + " ===");
        System.out.println("Описание: " + description);
        for (Product p : products) {
            System.out.println("  " + p);
        }
    }
}