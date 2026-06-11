package Exeception;

public class InsufficientFundsException extends ShopException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
// недостаточно денег