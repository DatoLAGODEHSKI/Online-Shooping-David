import java.util.Date;

public class Order {
    private static int orderCounter = 0;
    private int id;
    private Product product;
    private Client client;
    private double amount;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private Date date;

    public Order(Product product, Client client, double amount) {
        this.id = ++orderCounter;
        this.product = product;
        this.client = client;
        this.amount = amount;
        this.orderStatus = OrderStatus.НОВЫЙ;
        this.paymentStatus = PaymentStatus.НЕУДАЧА;
        this.date = new Date();
    }

    public void setOrderStatus(OrderStatus s) { orderStatus = s; }
    public void setPaymentStatus(PaymentStatus s) { paymentStatus = s; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }

    @Override
    public String toString() {
        return "Заказ №" + id + " от " + date + ": " + product.getName() + " на " + amount + " руб. Заказ: " + orderStatus + ", оплата: " + paymentStatus;
    }
}