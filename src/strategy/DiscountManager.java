package strategy;

import model.Order;

public class DiscountManager {
    private DiscountStrategy strategy;
    public void setDiscountStrategy(DiscountStrategy strategy) { this.strategy = strategy; }
    public double apply(Order order) {
        if (strategy == null) return 0;
        return strategy.calculate(order);
    }
}
