package interfaces;

import models.Client;

@FunctionalInterface
public interface PaymentStrategy {
    void pay(Client client, double amount);
}