package interfaces;

public interface Finansible {
    double checkBalance();
    boolean hasEnoughMoney(double amount);
    String getFinancialStatus();
}