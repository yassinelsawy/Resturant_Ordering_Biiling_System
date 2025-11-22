package decorator;

import model.MenuItem;

public class ExtraSaucePizza extends PizzaDecorator {
    public ExtraSaucePizza(MenuItem item) { super(item); }
    public double getPrice() { return item.getPrice() + 0.5; }
}
