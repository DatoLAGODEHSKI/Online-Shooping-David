package Sort;

import models.*;

public class DataInitializer {
    private Catalog catalog;
    private Client client;

    public void init() {
        catalog = Catalog.getInstance("Мой магазин");
        client = new Client("Иван Петров", "ivan@mail.ru", 5000);

        System.out.println("Добро пожаловать, " + client.getName() + "!");
        System.out.println(client);

        // Создание категорий
        Category phones = new Category("Телефоны", "Мобильные телефоны");
        Category electronics = new Category("Электроника", "Бытовая техника");
        Category garden = new Category("Сад", "Товары для сада");
        Category accessories = new Category("Аксессуары", "Чехлы, наушники, зарядные устройства");

        catalog.addCategory(phones);
        catalog.addCategory(electronics);
        catalog.addCategory(garden);
        catalog.addCategory(accessories);

        // Товары для телефонов
        Product iphone = new MobileDevice("iPhone 15", 1000, "Флагман Apple", "Apple", 12, 3200, 6.1);
        Product samsung = new MobileDevice("Samsung S24", 900, "Флагман Samsung", "Samsung", 24, 4000, 6.2);
        Product xiaomi = new MobileDevice("Xiaomi 14", 600, "Доступный флагман", "Xiaomi", 12, 4600, 6.4);

        phones.addProduct(iphone);
        phones.addProduct(samsung);
        phones.addProduct(xiaomi);

        // Товары для электроники
        Product laptop = new Electronic("Ноутбук Dell", 1200, "Мощный ноутбук", "Dell", 24);
        Product tv = new Electronic("Телевизор Samsung", 800, "4K Smart TV", "Samsung", 36);

        electronics.addProduct(laptop);
        electronics.addProduct(tv);

        // Товары для сада
        Product shovel = new GardenItem("Лопата", 25, "Стальная лопата", "Сталь");
        Product pot = new GardenItem("Горшок", 15, "Керамический горшок", "Керамика");

        garden.addProduct(shovel);
        garden.addProduct(pot);

        // Товары для аксессуаров
        Product phoneCase = new GardenItem("Чехол для iPhone", 30, "Силиконовый чехол", "Силикон");
        Product headphones = new Electronic("Наушники Sony", 150, "Беспроводные наушники", "Sony", 12);
        Product charger = new Electronic("Зарядное устройство", 25, "Быстрая зарядка", "Xiaomi", 6);
        Product powerBank = new Electronic("Power Bank", 45, "Внешний аккумулятор", "Xiaomi", 12);

        accessories.addProduct(phoneCase);
        accessories.addProduct(headphones);
        accessories.addProduct(charger);
        accessories.addProduct(powerBank);
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public Client getClient() {
        return client;
    }
}