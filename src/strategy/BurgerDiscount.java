package strategy;

import model.Order;

public class BurgerDiscount implements DiscountStrategy {
    public double calculate(Order order) { return order.calculateTotal() * 0.08; }
}
