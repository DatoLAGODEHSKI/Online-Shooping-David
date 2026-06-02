import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = Catalog.getInstance();

        // Создаём товары через фабрику
        Product iphone = ProductFactory.createProduct("mobile", "iPhone 15", 120000, "Флагман", "черный", "Apple", 12, 6.1);
        Product samsung = ProductFactory.createProduct("mobile", "Samsung S24", 110000, "AI телефон", "фиолетовый", "Samsung", 12, 6.2);
        Product laptop = ProductFactory.createProduct("electronic", "MacBook Pro", 250000, "Для работы", "серебристый", "Apple", 24);
        Product shovel = ProductFactory.createProduct("garden", "Лопата", 1500, "Садовая", "серая", "металл");
        Product hose = ProductFactory.createProduct("garden", "Шланг", 3000, "Поливочный", "зеленый", "резина");

        Category electronics = new ConcreteCategory("Электроника", "Гаджеты");
        Category garden = new ConcreteCategory("Сад", "Всё для сада");
        electronics.addProduct(iphone);
        electronics.addProduct(samsung);
        electronics.addProduct(laptop);
        garden.addProduct(shovel);
        garden.addProduct(hose);
        catalog.addCategory(electronics);
        catalog.addCategory(garden);

        Client client1 = new Client("Тамаз", 500000, UserRole.ВИП);
        Client client2 = new Client("Бабиджон", 200000, UserRole.ПРЕМИУМ);
        Client client3 = new Client("Ангелина", 100000, UserRole.ОБЫЧНЫЙ);
        List<Client> allClients = Arrays.asList(client1, client2, client3);
        Client currentClient = client1;

        List<Order> orders = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n======== МЕНЮ ========");
            System.out.println("1. Категории");
            System.out.println("2. Товары в категории");
            System.out.println("3. Сортировка товаров в категории");
            System.out.println("4. Купить товар");
            System.out.println("5. Баланс клиента");
            System.out.println("6. Stream: фильтр по цене");
            System.out.println("7. Stream: самый дешёвый / дорогой");
            System.out.println("8. Stream: поиск по названию");
            System.out.println("9. Все заказы");
            System.out.println("10. Оплаченные/неоплаченные заказы");
            System.out.println("11. Сменить клиента");
            System.out.println("12. Сменить роль");
            System.out.println("13. Пополнить баланс");
            System.out.println("14. Показать транзакции");
            System.out.println("15. Выход");
            System.out.print("Выберите: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> catalog.showCategories();
                case 2 -> {
                    System.out.print("Категория: ");
                    String title = sc.nextLine();
                    catalog.getCategories().stream()
                            .filter(c -> c.getTitle().equalsIgnoreCase(title))
                            .findFirst()
                            .ifPresentOrElse(Category::showInfo, () -> System.out.println("Нет категории"));
                }
                case 3 -> {
                    System.out.print("Категория: ");
                    String title = sc.nextLine();
                    Category cat = catalog.getCategories().stream()
                            .filter(c -> c.getTitle().equalsIgnoreCase(title))
                            .findFirst().orElse(null);
                    if (cat == null) {
                        System.out.println("Нет категории");
                        break;
                    }
                    System.out.println("Сортировать: 1-цена 2-название 3-цвет");
                    int sort = sc.nextInt();
                    Comparator<Product> comp = switch (sort) {
                        case 1 -> ProductComparator.byPrice;
                        case 2 -> ProductComparator.byName;
                        case 3 -> ProductComparator.byColor;
                        default -> ProductComparator.byPrice;
                    };
                    cat.getProducts().sort(comp);
                    cat.showInfo();
                }
                case 4 -> {
                    System.out.print("Товар: ");
                    String prodName = sc.nextLine();
                    Product found = catalog.getCategories().stream()
                            .flatMap(c -> c.getProducts().stream())
                            .filter(p -> p.getName().equalsIgnoreCase(prodName))
                            .findFirst().orElse(null);
                    if (found == null) {
                        System.out.println("Товар не найден");
                        break;
                    }
                    double finalPrice = found.getFinalPrice() * (1 - currentClient.getDiscount());
                    if (currentClient.hasEnoughMoney(finalPrice)) {
                        currentClient.pay(finalPrice);
                        found.pay(finalPrice);
                        Order order = new Order(found, currentClient, finalPrice);
                        order.setPaymentStatus(PaymentStatus.УСПЕХ);
                        order.setOrderStatus(OrderStatus.ОПЛАЧЕН);
                        orders.add(order);
                        transactions.add(new Transaction("ПОКУПКА", finalPrice, currentClient.getName(), found.getName()));
                        System.out.printf("Оплачено %.2f руб. (скидка %.0f%%)\n", finalPrice, currentClient.getDiscount() * 100);
                    } else {
                        System.out.println("Не хватает денег");
                        Order order = new Order(found, currentClient, finalPrice);
                        order.setPaymentStatus(PaymentStatus.НЕ_ХВАТАЕТ);
                        order.setOrderStatus(OrderStatus.ОТМЕНЕН);
                        orders.add(order);
                        transactions.add(new Transaction("ОТМЕНА", finalPrice, currentClient.getName(), found.getName()));
                    }
                }
                case 5 -> System.out.println(currentClient.getFinalStatus());
                case 6 -> {
                    System.out.print("Мин. цена: ");
                    double min = sc.nextDouble();
                    System.out.print("Макс. цена: ");
                    double max = sc.nextDouble();
                    catalog.getCategories().stream()
                            .flatMap(c -> c.getProducts().stream())
                            .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                            .forEach(System.out::println);
                }
                case 7 -> {
                    var all = catalog.getCategories().stream().flatMap(c -> c.getProducts().stream());
                    Product cheapest = all.min(ProductComparator.byPrice).orElse(null);
                    Product mostExpensive = all.max(ProductComparator.byPrice).orElse(null);
                    System.out.println("Самый дешёвый: " + cheapest);
                    System.out.println("Самый дорогой: " + mostExpensive);
                }
                case 8 -> {
                    System.out.print("Часть названия: ");
                    String part = sc.nextLine().toLowerCase();
                    catalog.getCategories().stream()
                            .flatMap(c -> c.getProducts().stream())
                            .filter(p -> p.getName().toLowerCase().contains(part))
                            .forEach(System.out::println);
                }
                case 9 -> orders.forEach(System.out::println);
                case 10 -> {
                    System.out.println("Оплаченные заказы:");
                    orders.stream().filter(o -> o.getPaymentStatus() == PaymentStatus.УСПЕХ).forEach(System.out::println);
                    System.out.println("Неоплаченные заказы:");
                    orders.stream().filter(o -> o.getPaymentStatus() != PaymentStatus.УСПЕХ).forEach(System.out::println);
                }
                case 11 -> {
                    for (int i = 0; i < allClients.size(); i++)
                        System.out.println((i+1) + ". " + allClients.get(i).getName());
                    int idx = sc.nextInt() - 1;
                    if (idx >= 0 && idx < allClients.size()) currentClient = allClients.get(idx);
                }
                case 12 -> {
                    System.out.println("1. Обычный 2. Премиум 3. ВИП");
                    int r = sc.nextInt();
                    currentClient.setRole(r == 1 ? UserRole.ОБЫЧНЫЙ : r == 2 ? UserRole.ПРЕМИУМ : UserRole.ВИП);
                    System.out.println("Новая роль: " + currentClient.getRole());
                }
                case 13 -> {
                    System.out.print("Сумма: ");
                    double sum = sc.nextDouble();
                    currentClient.addBalance(sum);
                    transactions.add(new Transaction("ПОПОЛНЕНИЕ", sum, currentClient.getName(), "-"));
                }
                case 14 -> transactions.forEach(System.out::println);
                case 15 -> exit = true;
                default -> System.out.println("Неверный выбор");
            }
        }
        sc.close();
    }
}