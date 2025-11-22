package strategy;

import model.Order;

public class PizzaDiscount implements DiscountStrategy {
    public double calculate(Order order) { return order.calculateTotal() * 0.1; }
}
