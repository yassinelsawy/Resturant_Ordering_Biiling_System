package decorator;

import model.MenuItem;

public class Lemon extends DrinkDecorator {
    public Lemon(MenuItem item) { super(item); }
    public double getPrice() { return item.getPrice() + 0.3; }
}
