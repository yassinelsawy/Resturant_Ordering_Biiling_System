package decorator;

import model.MenuItem;

public class ExtraCheesePizza extends PizzaDecorator {
    public ExtraCheesePizza(MenuItem item) { super(item); }
    public double getPrice() { return item.getPrice() + 1.0; }
}
