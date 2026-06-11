package Exeception;

public class PaymentFailedException extends ShopException {
    public PaymentFailedException(String message) {
        super(message);
    }
}