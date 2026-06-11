package models;

import enums.OrderStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order {
    private String id;
    private Client client;
    private List<Product> products;
    private LocalDateTime dateTime;
    private OrderStatus status;
    private double totalAmount;

    public Order(Client client, List<Product> products) {
        this.id = "ORD" + System.currentTimeMillis() % 10000;
        this.client = client;
        this.products = products;
        this.dateTime = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.totalAmount = products.stream().mapToDouble(Product::getPrice).sum();
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getId() { return id; }
    public OrderStatus getStatus() { return status; }
    public double getTotalAmount() { return totalAmount; }
    public List<Product> getProducts() { return products; }

    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

    public String toString() {
        return "Заказ " + id + " от " + getFormattedDateTime() + " на сумму " + totalAmount + " руб. - " + status.getRusName();
    }
}