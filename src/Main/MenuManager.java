package Main;

import Sort.SortByName;
import Sort.SortByPrice;
import enums.OrderStatus;
import models.*;

import java.util.*;

public class MenuManager {
    private Scanner scanner;
    private Client currentClient;
    private Catalog catalog;
    private Category currentCategory;
    private List<Order> orders;

    public MenuManager() {
        this.scanner = new Scanner(System.in);
        this.orders = new ArrayList<>();
    }

    public void start(Client client, Catalog catalog) {
        this.currentClient = client;
        this.catalog = catalog;

        while (true) {
            System.out.println("\n========== МЕНЮ ==========");
            System.out.println("1. Все категории");
            System.out.println("2. Выбрать категорию");
            System.out.println("3. Товары в категории");
            System.out.println("4. Сортировать товары");
            System.out.println("5. Сравнить товары");
            System.out.println("6. Найти товары");
            System.out.println("7. Статистика");
            System.out.println("8. Мои заказы");
            System.out.println("9. Купить товар");
            System.out.println("10. Добавить категорию");
            System.out.println("11. Добавить товар");
            System.out.println("12. Удалить категорию");
            System.out.println("13. Удалить товар");
            System.out.println("14. Мой профиль");
            System.out.println("15. Пополнить баланс");
            System.out.println("0. Выход");
            System.out.println("==========================");

            if (currentCategory != null) {
                System.out.println("Текущая категория: " + currentCategory.getTitle());
            }
            System.out.println("Баланс: " + currentClient.checkBalance() + " руб.");

            System.out.print("Выберите: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("До свидания!");
                break;
            }

            switch (choice) {
                case 1: showCategories(); break;
                case 2: selectCategory(); break;
                case 3: showProducts(); break;
                case 4: sortProducts(); break;
                case 5: compareProducts(); break;
                case 6: filterProducts(); break;
                case 7: showStats(); break;
                case 8: showOrders(); break;
                case 9: buyProduct(); break;
                case 10: addCategory(); break;
                case 11: addProduct(); break;
                case 12: deleteCategory(); break;
                case 13: deleteProduct(); break;
                case 14: showClientInfo(); break;
                case 15: addMoney(); break;
                default: System.out.println("Неверный выбор!");
            }
        }
        scanner.close();
    }

    private void showCategories() {
        System.out.println("\n--- ВСЕ КАТЕГОРИИ ---");
        ArrayList<Category> cats = catalog.getCategories();

        if (cats.isEmpty()) {
            System.out.println("Нет категорий");
            return;
        }

        Collections.sort(cats);

        for (int i = 0; i < cats.size(); i++) {
            Category cat = cats.get(i);
            System.out.println((i+1) + ". " + cat.getTitle() + " (" + cat.getProducts().size() + " товаров)");
            System.out.println("   " + cat.getDescription());
        }
    }

    private void selectCategory() {
        ArrayList<Category> cats = catalog.getCategories();

        if (cats.isEmpty()) {
            System.out.println("Нет категорий!");
            return;
        }

        System.out.println("\n--- ВЫБОР КАТЕГОРИИ ---");
        for (int i = 0; i < cats.size(); i++) {
            System.out.println((i+1) + ". " + cats.get(i).getTitle());
        }

        System.out.print("Номер: ");
        int num = scanner.nextInt() - 1;

        if (num >= 0 && num < cats.size()) {
            currentCategory = cats.get(num);
            System.out.println("Выбрана: " + currentCategory.getTitle());
        } else {
            System.out.println("Категория не найдена!");
        }
    }

    private void showProducts() {
        if (currentCategory == null) {
            System.out.println("Сначала выберите категорию!");
            return;
        }

        System.out.println("\n--- " + currentCategory.getTitle() + " ---");
        List<Product> products = currentCategory.getProducts();

        if (products.isEmpty()) {
            System.out.println("Нет товаров");
            return;
        }

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i+1) + ". " + p.getTitle() + " - " + p.getPrice() + " руб.");
            System.out.println("   " + p.getDescription());

            if (p instanceof Electronic) {
                Electronic e = (Electronic) p;
                System.out.println("   Бренд: " + e.brand);
            }
            if (p instanceof MobileDevice) {
                MobileDevice m = (MobileDevice) p;
                System.out.println("   Батарея: " + m.batteryCapacity + " mAh");
            }
            if (p instanceof GardenItem) {
                GardenItem g = (GardenItem) p;
                System.out.println("   Материал: " + g.material);
            }
            System.out.println();
        }
    }

    private void sortProducts() {
        if (currentCategory == null) {
            System.out.println("Сначала выберите категорию!");
            return;
        }

        List<Product> products = new ArrayList<>(currentCategory.getProducts());
        if (products.isEmpty()) {
            System.out.println("Нет товаров!");
            return;
        }

        System.out.println("\n--- СОРТИРОВКА ---");
        System.out.println("1. По цене (дешевые)");
        System.out.println("2. По цене (дорогие)");
        System.out.println("3. По названию");
        System.out.print("Выберите: ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            products.sort(new SortByPrice());
            System.out.println("Отсортировано по цене (дешевые):");
        } else if (choice == 2) {
            products.sort(new SortByPrice().reversed());
            System.out.println("Отсортировано по цене (дорогие):");
        } else if (choice == 3) {
            products.sort(new SortByName());
            System.out.println("Отсортировано по названию:");
        } else {
            System.out.println("Неверный выбор!");
            return;
        }

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i+1) + ". " + p.getTitle() + " - " + p.getPrice() + " руб.");
        }
    }

    private void compareProducts() {
        if (currentCategory == null) {
            System.out.println("Сначала выберите категорию!");
            return;
        }

        List<Product> products = currentCategory.getProducts();
        if (products.size() < 2) {
            System.out.println("Нужно минимум 2 товара!");
            return;
        }

        System.out.println("\n--- СРАВНЕНИЕ ---");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i+1) + ". " + products.get(i).getTitle());
        }

        System.out.print("Первый товар: ");
        int first = scanner.nextInt() - 1;
        System.out.print("Второй товар: ");
        int second = scanner.nextInt() - 1;

        if (first < 0 || first >= products.size() || second < 0 || second >= products.size()) {
            System.out.println("Товар не найден!");
            return;
        }

        if (first == second) {
            System.out.println("Нельзя сравнить с собой!");
            return;
        }

        Product p1 = products.get(first);
        Product p2 = products.get(second);

        System.out.println("\n1. " + p1.getTitle() + " - " + p1.getPrice() + " руб.");
        System.out.println("2. " + p2.getTitle() + " - " + p2.getPrice() + " руб.");

        if (p1.getPrice() < p2.getPrice()) {
            System.out.println(p1.getTitle() + " дешевле на " + (p2.getPrice() - p1.getPrice()) + " руб.");
        } else if (p1.getPrice() > p2.getPrice()) {
            System.out.println(p2.getTitle() + " дешевле на " + (p1.getPrice() - p2.getPrice()) + " руб.");
        } else {
            System.out.println("Цены одинаковые");
        }
    }

    private void filterProducts() {
        if (currentCategory == null) {
            System.out.println("Сначала выберите категорию!");
            return;
        }

        List<Product> products = currentCategory.getProducts();
        if (products.isEmpty()) {
            System.out.println("Нет товаров!");
            return;
        }

        System.out.println("\n--- ПОИСК ТОВАРОВ ---");
        System.out.println("1. Дешевле 1000 руб.");
        System.out.println("2. Дороже 500 руб.");
        System.out.println("3. Поиск по названию");
        System.out.print("Выберите: ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("\nТовары дешевле 1000 руб.:");
            for (Product p : products) {
                if (p.getPrice() < 1000) {
                    System.out.println("   " + p.getTitle() + " - " + p.getPrice() + " руб.");
                }
            }
        } else if (choice == 2) {
            System.out.println("\nТовары дороже 500 руб.:");
            for (Product p : products) {
                if (p.getPrice() > 500) {
                    System.out.println("   " + p.getTitle() + " - " + p.getPrice() + " руб.");
                }
            }
        } else if (choice == 3) {
            System.out.print("Введите название: ");
            scanner.nextLine();
            String search = scanner.nextLine();
            System.out.println("\nРезультаты:");
            for (Product p : products) {
                if (p.getTitle().toLowerCase().contains(search.toLowerCase())) {
                    System.out.println("   " + p.getTitle() + " - " + p.getPrice() + " руб.");
                }
            }
        } else {
            System.out.println("Неверный выбор!");
        }
    }

    private void showStats() {
        System.out.println("\n--- СТАТИСТИКА ---");

        int totalCategories = catalog.getCategories().size();
        int totalProducts = 0;
        for (Category cat : catalog.getCategories()) {
            totalProducts += cat.getProducts().size();
        }

        System.out.println("Категорий: " + totalCategories);
        System.out.println("Всего товаров: " + totalProducts);
        System.out.println("Всего заказов: " + orders.size());

        System.out.println("\nПо категориям:");
        for (Category cat : catalog.getCategories()) {
            System.out.println("   " + cat.getTitle() + ": " + cat.getProducts().size() + " товаров");
        }
    }

    private void showOrders() {
        if (orders.isEmpty()) {
            System.out.println("У вас нет заказов!");
            return;
        }

        System.out.println("\n--- МОИ ЗАКАЗЫ ---");
        double total = 0;

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println((i+1) + ". " + order);
            total += order.getTotalAmount();
        }

        System.out.println("\nВсего потрачено: " + total + " руб.");
    }

    private void buyProduct() {
        if (currentCategory == null) {
            System.out.println("Сначала выберите категорию!");
            return;
        }

        List<Product> products = currentCategory.getProducts();
        if (products.isEmpty()) {
            System.out.println("Нет товаров для покупки!");
            return;
        }

        System.out.println("\n--- ПОКУПКА ---");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i+1) + ". " + p.getTitle() + " - " + p.getPrice() + " руб.");
        }

        System.out.print("Выберите товар: ");
        int choice = scanner.nextInt() - 1;

        if (choice < 0 || choice >= products.size()) {
            System.out.println("Товар не найден!");
            return;
        }

        Product product = products.get(choice);
        double price = product.getPrice();

        if (currentClient.hasEnoughMoney(price)) {
            currentClient.withdraw(price);
            product.pay(price);

            List<Product> bought = new ArrayList<>();
            bought.add(product);
            Order order = new Order(currentClient, bought);
            order.setStatus(OrderStatus.PAID);
            orders.add(order);

            System.out.println("Поздравляем! " + product.getTitle() + " куплен!");
            System.out.println("Остаток на счете: " + currentClient.checkBalance() + " руб.");
        } else {
            System.out.println("Не хватает денег! Нужно: " + price + ", есть: " + currentClient.checkBalance());
        }
    }

    private void addCategory() {
        System.out.println("\n--- НОВАЯ КАТЕГОРИЯ ---");
        System.out.print("Название: ");
        scanner.nextLine();
        String title = scanner.nextLine();

        if (title.trim().isEmpty()) {
            System.out.println("Название не может быть пустым!");
            return;
        }

        System.out.print("Описание: ");
        String desc = scanner.nextLine();

        Category newCat = new Category(title, desc);
        catalog.addCategory(newCat);

        System.out.println("Категория '" + title + "' добавлена!");
    }

    private void addProduct() {
        if (currentCategory == null) {
            System.out.println("Сначала выберите категорию!");
            return;
        }

        System.out.println("\n--- НОВЫЙ ТОВАР ---");
        System.out.println("Тип товара:");
        System.out.println("1. Электроника");
        System.out.println("2. Телефон");
        System.out.println("3. Для сада");
        System.out.print("Выберите: ");

        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Название: ");
        String title = scanner.nextLine();

        System.out.print("Цена: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Описание: ");
        String desc = scanner.nextLine();

        Product product = null;

        if (type == 1) {
            System.out.print("Бренд: ");
            String brand = scanner.nextLine();
            System.out.print("Гарантия (мес): ");
            int warranty = scanner.nextInt();
            product = new Electronic(title, price, desc, brand, warranty);
        } else if (type == 2) {
            System.out.print("Бренд: ");
            String brand = scanner.nextLine();
            System.out.print("Гарантия (мес): ");
            int warranty = scanner.nextInt();
            System.out.print("Батарея (mAh): ");
            int battery = scanner.nextInt();
            System.out.print("Экран (дюймы): ");
            double screen = scanner.nextDouble();
            product = new MobileDevice(title, price, desc, brand, warranty, battery, screen);
        } else if (type == 3) {
            System.out.print("Материал: ");
            String material = scanner.nextLine();
            product = new GardenItem(title, price, desc, material);
        } else {
            System.out.println("Неверный тип!");
            return;
        }

        currentCategory.addProduct(product);
        System.out.println("Товар '" + title + "' добавлен!");
    }

    private void deleteCategory() {
        ArrayList<Category> cats = catalog.getCategories();

        if (cats.isEmpty()) {
            System.out.println("Нет категорий для удаления!");
            return;
        }

        System.out.println("\n--- УДАЛЕНИЕ КАТЕГОРИИ ---");
        for (int i = 0; i < cats.size(); i++) {
            System.out.println((i+1) + ". " + cats.get(i).getTitle());
        }

        System.out.print("Номер: ");
        int num = scanner.nextInt() - 1;

        if (num >= 0 && num < cats.size()) {
            Category removed = cats.get(num);

            if (currentCategory != null && currentCategory.getTitle().equals(removed.getTitle())) {
                currentCategory = null;
            }

            catalog.getCategories().remove(num);
            System.out.println("Категория '" + removed.getTitle() + "' удалена!");
        } else {
            System.out.println("Категория не найдена!");
        }
    }

    private void deleteProduct() {
        if (currentCategory == null) {
            System.out.println("Сначала выберите категорию!");
            return;
        }

        List<Product> products = currentCategory.getProducts();
        if (products.isEmpty()) {
            System.out.println("Нет товаров для удаления!");
            return;
        }

        System.out.println("\n--- УДАЛЕНИЕ ТОВАРА ---");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i+1) + ". " + products.get(i).getTitle());
        }

        System.out.print("Номер: ");
        int num = scanner.nextInt() - 1;

        if (num >= 0 && num < products.size()) {
            Product removed = products.get(num);
            products.remove(num);
            System.out.println("Товар '" + removed.getTitle() + "' удален!");
        } else {
            System.out.println("Товар не найден!");
        }
    }

    private void showClientInfo() {
        System.out.println("\n--- МОЙ ПРОФИЛЬ ---");
        System.out.println("Имя: " + currentClient.getName());
        System.out.println("Email: " + currentClient.getEmail());
        System.out.println("Баланс: " + currentClient.checkBalance() + " руб.");
        System.out.println("Статус: " + currentClient.getFinancialStatus());
        System.out.println("Заказов: " + orders.size());

        double total = 0;
        for (Order o : orders) {
            if (o.getStatus() == OrderStatus.PAID) {
                total += o.getTotalAmount();
            }
        }
        System.out.println("Всего потрачено: " + total + " руб.");
    }

    private void addMoney() {
        System.out.println("\n--- ПОПОЛНЕНИЕ БАЛАНСА ---");
        System.out.print("Сумма: ");
        double amount = scanner.nextDouble();

        currentClient.addMoney(amount);
    }
}