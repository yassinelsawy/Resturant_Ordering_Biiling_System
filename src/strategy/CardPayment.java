package strategy;

public class CardPayment implements PaymentStrategy {
    public boolean pay(double amount) {
        System.out.println("Paid $" + amount + " by Card.");
        return true;
    }
}
