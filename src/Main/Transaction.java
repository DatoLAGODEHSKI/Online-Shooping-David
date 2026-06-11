package Main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Transaction {
    private final LocalDateTime dateTime;
    private final String type;
    private final double amount;
    private final String clientName;
    private final String productName;

    public Transaction(String type, double amount, String clientName, String productName) {
        this.dateTime = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.clientName = clientName;
        this.productName = productName;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return String.format("%s | %s | %.2f руб. | Клиент: %s | Товар: %s",
                dateTime.format(formatter), type, amount, clientName, productName);
    }
}