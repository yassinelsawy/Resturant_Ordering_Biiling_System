package factory;

import model.CheeseBurger;
import model.ClassicBurger;
import model.MenuItem;

public class BurgerFactory {
    public MenuItem createBurger(String type) {
        if (type.equalsIgnoreCase("Classic")) return new ClassicBurger();
        else return new CheeseBurger();
    }
}
