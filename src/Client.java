public class Client implements Finansible {
    String id;
    String name;
    String email;
    double balance;

    public Client(String name, String email, double balance) {
        this.id = "CL" + System.currentTimeMillis() % 10000;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public double checkBalance() {
        return balance;
    }

    public boolean hasEnoughMoney(double amount) {
        return balance >= amount;
    }

    public String getFinancialStatus() {
        if (balance > 10000) return "Платежеспособный";
        if (balance > 1000) return "Стабильный";
        return "Требуется пополнение";
    }

    public void withdraw(double amount) {
        if (hasEnoughMoney(amount)) {
            balance -= amount;
            System.out.println("Списано " + amount + " руб. Остаток: " + balance + " руб.");
        } else {
            System.out.println("Недостаточно средств!");
        }
    }

    public void addMoney(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Баланс пополнен на " + amount + " руб.");
        } else {
            System.out.println("Сумма должна быть больше 0!");
        }
    }

    public String getName() { return name; }
    public String getEmail() { return email; }

    public String toString() {
        return name + ", баланс: " + balance + " руб.";
    }
}