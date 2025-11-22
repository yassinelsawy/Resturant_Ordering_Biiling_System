package decorator;

import model.MenuItem;

public class ExtraCheeseBurger extends BurgerDecorator {
    public ExtraCheeseBurger(MenuItem item) { super(item); }
    public double getPrice() { return item.getPrice() + 0.8; }
}
