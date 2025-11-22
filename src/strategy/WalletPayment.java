package strategy;

public class WalletPayment implements PaymentStrategy {
    public boolean pay(double amount) {
        System.out.println("Paid $" + amount + " by Wallet.");
        return true;
    }
}
