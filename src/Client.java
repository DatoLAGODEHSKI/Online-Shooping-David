public class Client implements Finansible {
    private static int idCounter = 0;
    private int id;
    private String name;
    private double balance;
    private UserRole role;

    public Client(String name, double balance, UserRole role) {
        this.id = ++idCounter;
        this.name = name;
        this.balance = balance;
        this.role = role;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

    public double getDiscount() {
        if (role == UserRole.ВИП) return 0.20;
        if (role == UserRole.ПРЕМИУМ) return 0.10;
        return 0;
    }

    @Override
    public double checkBalance() { return balance; }
    @Override
    public boolean hasEnoughMoney(double amount) { return balance >= amount; }
    @Override
    public String getFinalStatus() {
        return "Клиент " + name + ", баланс: " + balance + " руб., роль: " + role;
    }

    public void pay(double amount) {
        if (hasEnoughMoney(amount)) {
            balance -= amount;
            System.out.println("Оплачено " + amount + " руб.");
        } else {
            System.out.println("Недостаточно денег");
        }
    }

    public void addBalance(double sum) {
        balance += sum;
        System.out.println("Баланс пополнен на " + sum);
    }

    @Override
    public String toString() {
        return name + " (баланс=" + balance + ", роль=" + role + ")";
    }
}