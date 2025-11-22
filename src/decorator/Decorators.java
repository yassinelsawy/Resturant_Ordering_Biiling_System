package decorator;

import model.MenuItem;

// Pizza Decorators
abstract class PizzaDecorator extends MenuItem {
    protected MenuItem item;
    public PizzaDecorator(MenuItem item) { this.item = item; }
    public String getName() { return item.getName() + " + " + this.getClass().getSimpleName(); }
    public double getPrice() { return item.getPrice(); }
}

// Burger Decorators
abstract class BurgerDecorator extends MenuItem {
    protected MenuItem item;
    public BurgerDecorator(MenuItem item) { this.item = item; }
    public String getName() { return item.getName() + " + " + this.getClass().getSimpleName(); }
    public double getPrice() { return item.getPrice(); }
}

// Drink Decorators
abstract class DrinkDecorator extends MenuItem {
    protected MenuItem item;
    public DrinkDecorator(MenuItem item) { this.item = item; }
    public String getName() { return item.getName() + " + " + this.getClass().getSimpleName(); }
    public double getPrice() { return item.getPrice(); }
}

