package enums;

public enum PaymentStatus {
    NOT_PAID("Не оплачен"),
    PAID("Оплачен"),
    PARTIALLY_PAID("Частично оплачен"),
    REFUNDED("Возвращен");

    private String rusName;

    PaymentStatus(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }
}