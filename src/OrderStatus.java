public enum OrderStatus {
    PENDING("В обработке"),
    PAID("Оплачен"),
    SHIPPED("Отправлен"),
    DELIVERED("Доставлен"),
    CANCELLED("Отменен");

    private String rusName;

    OrderStatus(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }
}