package factory;

import model.EasternPizza;
import model.ItalianPizza;
import model.MenuItem;

// Simple Factories
public class PizzaFactory {
    public MenuItem createPizza(String type) {
        if (type.equalsIgnoreCase("Italian")) return new ItalianPizza();
        else return new EasternPizza();
    }
}
