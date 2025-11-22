package strategy;

public class PaymentController {
    private PaymentStrategy paymentStrategy;
    public void setPaymentMethod(PaymentStrategy strategy) { this.paymentStrategy = strategy; }
    public boolean pay(double amount) {
        if (paymentStrategy == null) return false;
        return paymentStrategy.pay(amount);
    }
}
