package factory;

public class VegetarianMenuFactory implements MenuFactory {
    public PizzaFactory createPizzaFactory() { return new PizzaFactory(); }
    public BurgerFactory createBurgerFactory() { return null; }
    public DrinkFactory createDrinkFactory() { return new DrinkFactory(); }
}
