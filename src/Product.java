// Абстрактный товар с description (этап 2-1)
public abstract class Product {
    private static int idCounter = 0;
    protected int id;
    protected String name;
    protected double price;
    protected String description;
    protected String color;
    protected boolean paid;

    public Product(String name, double price, String description, String color) {
        this.id = ++idCounter;
        this.name = name;
        this.price = price;
        this.description = description;
        this.color = color;
        this.paid = false;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getColor() { return color; }
    public boolean isPaid() { return paid; }

    public double getFinalPrice() { return price; }
    public void pay(double amount) {
        if (amount >= getFinalPrice()) {
            paid = true;
            System.out.println(name + " оплачен");
        } else {
            System.out.println("Не хватает денег на " + name);
        }
    }

    @Override
    public String toString() {
        return id + ". " + name + " (" + price + " руб.) - " + description + ", цвет: " + color;
    }
}