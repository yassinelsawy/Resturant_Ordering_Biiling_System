package strategy;

import model.Order;

public interface DiscountStrategy {
    double calculate(Order order);
}

class SeasonalDiscount implements DiscountStrategy {
    public double calculate(Order order) { return order.calculateTotal() * 0.15; }
}

