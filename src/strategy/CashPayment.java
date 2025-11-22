package strategy;

public class CashPayment implements PaymentStrategy {
    public boolean pay(double amount) {
        System.out.println("Paid $" + amount + " by Cash.");
        return true;
    }
}
