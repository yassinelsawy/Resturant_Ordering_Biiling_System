package factory;

import model.Cola;
import model.Juice;
import model.MenuItem;

public class DrinkFactory {
    public MenuItem createDrink(String type) {
        if (type.equalsIgnoreCase("Cola")) return new Cola();
        else return new Juice();
    }
}
