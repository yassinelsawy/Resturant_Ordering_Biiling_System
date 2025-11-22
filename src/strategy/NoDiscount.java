package strategy;

import model.Order;

public class NoDiscount implements DiscountStrategy {
    public double calculate(Order order) { return 0; }
}
