package decorator;

import model.MenuItem;

public class ExtraSauceBurger extends BurgerDecorator {
    public ExtraSauceBurger(MenuItem item) { super(item); }
    public double getPrice() { return item.getPrice() + 0.5; }
}
