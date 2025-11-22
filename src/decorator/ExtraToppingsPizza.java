package decorator;

import model.MenuItem;

public class ExtraToppingsPizza extends PizzaDecorator {
    public ExtraToppingsPizza(MenuItem item) { super(item); }
    public double getPrice() { return item.getPrice() + 1.5; }
}
