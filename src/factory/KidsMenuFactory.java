package factory;

public class KidsMenuFactory implements MenuFactory {
    public PizzaFactory createPizzaFactory() { return new PizzaFactory(); }
    public BurgerFactory createBurgerFactory() { return new BurgerFactory(); }
    public DrinkFactory createDrinkFactory() { return new DrinkFactory(); }
}
