package decorator;

import model.MenuItem;

public class Ice extends DrinkDecorator {
    public Ice(MenuItem item) { super(item); }
    public double getPrice() { return item.getPrice() + 0.2; }
}
