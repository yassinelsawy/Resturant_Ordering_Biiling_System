package decorator;

import model.MenuItem;

public class BaconTopping extends BurgerDecorator {
    public BaconTopping(MenuItem item) { super(item); }
    public double getPrice() { return item.getPrice() + 1.2; }
}
