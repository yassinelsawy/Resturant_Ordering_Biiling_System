package factory;

import model.*;

public interface MenuFactory {
    PizzaFactory createPizzaFactory();
    BurgerFactory createBurgerFactory();
    DrinkFactory createDrinkFactory();
}

