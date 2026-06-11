package models;

import interfaces.Payable;

import java.util.UUID;

public abstract class Product implements Payable {
    String id;
    String title;
    double price;
    String description;
    boolean paid;

    public Product(String title, double price, String description) {
        this.id = UUID.randomUUID().toString().substring(0, 6);
        this.title = title;
        this.price = price;
        this.description = description;
        this.paid = false;
    }

    public double getFinalPrice() {
        return price;
    }

    public void pay(double amount) {
        if (amount >= price) {
            paid = true;
        }
    }

    public boolean isPaid() {
        return paid;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
}