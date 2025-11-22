package strategy;

import model.Order;

public class DrinkDiscount implements DiscountStrategy {
    public double calculate(Order order) { return order.calculateTotal() * 0.05; }
}
