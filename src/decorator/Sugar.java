package decorator;

import model.MenuItem;

public class Sugar extends DrinkDecorator {
    public Sugar(MenuItem item) { super(item); }
    public double getPrice() { return item.getPrice() + 0.1; }
}
