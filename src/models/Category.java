package models;

import java.util.ArrayList;
import java.util.UUID;

public class Category implements Comparable<Category> {
    String id;
    String title;
    String description;
    ArrayList<Product> products;

    public Category(String title, String description) {
        this.id = UUID.randomUUID().toString().substring(0, 6);
        this.title = title;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void showInfo() {
        System.out.println("\n--- " + title + " ---");
        System.out.println("Описание: " + description);
        System.out.println("Товаров: " + products.size());

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println("   " + (i+1) + ". " + p.getTitle() + " - " + p.getPrice() + " руб.");
            if (p instanceof Electronic) {
                Electronic e = (Electronic) p;
                System.out.println("      Бренд: " + e.brand);
            }
        }
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public ArrayList<Product> getProducts() { return products; }

    public int compareTo(Category other) {
        return this.title.compareTo(other.title);
    }
}