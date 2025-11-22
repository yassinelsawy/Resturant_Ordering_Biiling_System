package controller;

import factory.*;
import strategy.*;
import observer.*;
import model.*;
import decorator.*;
import java.util.*;

public class OrderController {
    private MenuFactory menuFactory;
    private PaymentController paymentController;
    private DiscountManager discountManager;
    private OrderPublisher orderPublisher;
    private Order order;
    private Scanner scanner;

    public OrderController() {
        this.paymentController = new PaymentController();
        this.discountManager = new DiscountManager();
        this.orderPublisher = new OrderPublisher();
        this.scanner = new Scanner(System.in);
        // Subscribe observers
        orderPublisher.subscribe(new KitchenObserver());
        orderPublisher.subscribe(new WaiterObserver());
    }

    public void processOrder() {
        this.menuFactory = selectMenuType();
        String category = chooseCategory();
        MenuItem item = chooseItems(category);
        item = applyAddOns(item, category);
        this.order = new Order();
        order.addItem(item);
        applyDiscount();
        notifyKitchen();
        System.out.println("Order created successfully!");
    }

    protected MenuFactory selectMenuType() {
        System.out.println("Select Menu Type: 1. Vegetarian 2. NonVeg 3. Kids");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: return new VegetarianMenuFactory();
            case 2: return new NonVegMenuFactory();
            case 3: return new KidsMenuFactory();
            default: return new VegetarianMenuFactory();
        }
    }

    protected String chooseCategory() {
        System.out.println("Select Category: 1. Pizza 2. Burger 3. Drink");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: return "Pizza";
            case 2: return "Burger";
            case 3: return "Drink";
            default: return "Pizza";
        }
    }

    protected MenuItem chooseItems(String category) {
        if (category.equals("Pizza")) {
            PizzaFactory pizzaFactory = menuFactory.createPizzaFactory();
            System.out.println("Select Pizza: 1. Italian 2. Eastern");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) return pizzaFactory.createPizza("Italian");
            else return pizzaFactory.createPizza("Eastern");
        } else if (category.equals("Burger")) {
            BurgerFactory burgerFactory = menuFactory.createBurgerFactory();
            System.out.println("Select Burger: 1. Classic 2. Cheese");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) return burgerFactory.createBurger("Classic");
            else return burgerFactory.createBurger("Cheese");
        } else {
            DrinkFactory drinkFactory = menuFactory.createDrinkFactory();
            System.out.println("Select Drink: 1. Cola 2. Juice");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) return drinkFactory.createDrink("Cola");
            else return drinkFactory.createDrink("Juice");
        }
    }

    protected MenuItem applyAddOns(MenuItem item, String category) {
        System.out.println("Add-ons? (y/n): ");
        String ans = scanner.nextLine();
        if (!ans.equalsIgnoreCase("y")) return item;
        if (category.equals("Pizza")) {
            System.out.println("1. Extra Cheese 2. Extra Sauce 3. Extra Toppings");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: return new ExtraCheesePizza(item);
                case 2: return new ExtraSaucePizza(item);
                case 3: return new ExtraToppingsPizza(item);
                default: return item;
            }
        } else if (category.equals("Burger")) {
            System.out.println("1. Extra Cheese 2. Bacon 3. Extra Sauce");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: return new ExtraCheeseBurger(item);
                case 2: return new BaconTopping(item);
                case 3: return new ExtraSauceBurger(item);
                default: return item;
            }
        } else {
            System.out.println("1. Ice 2. Lemon 3. Sugar");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: return new Ice(item);
                case 2: return new Lemon(item);
                case 3: return new Sugar(item);
                default: return item;
            }
        }
    }

    protected void applyDiscount() {
        if (order == null || order.getItems().isEmpty()) return;
        MenuItem item = order.getItems().get(0);
        if (item.getName().contains("Pizza")) {
            discountManager.setDiscountStrategy(new PizzaDiscount());
        } else if (item.getName().contains("Burger")) {
            discountManager.setDiscountStrategy(new BurgerDiscount());
        } else if (item.getName().contains("Cola") || item.getName().contains("Juice")) {
            discountManager.setDiscountStrategy(new DrinkDiscount());
        } else {
            discountManager.setDiscountStrategy(new NoDiscount());
        }
        double discount = discountManager.apply(order);
        order.applyDiscount(discount);
        order.setFinalPrice(order.calculateTotal() - discount);
    }

    protected void notifyKitchen() {
        orderPublisher.notify(order);
    }

    public void handlePayment() {
        if (order == null) {
            System.out.println("No order to pay.");
            return;
        }
        System.out.println("Select Payment Method: 1. Cash 2. Card 3. Wallet");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: paymentController.setPaymentMethod(new CashPayment()); break;
            case 2: paymentController.setPaymentMethod(new CardPayment()); break;
            case 3: paymentController.setPaymentMethod(new WalletPayment()); break;
            default: paymentController.setPaymentMethod(new CashPayment());
        }
        boolean paid = paymentController.pay(order.getFinalPrice());
        if (paid) System.out.println("Payment successful!");
        else System.out.println("Payment failed!");
    }

    public void generateReceipt() {
        if (order == null) {
            System.out.println("No order to show.");
            return;
        }
        System.out.println("\n--- Receipt ---");
        for (MenuItem item : order.getItems()) {
            System.out.println(item.getName() + " : $" + item.getPrice());
        }
        System.out.println("Total: $" + order.calculateTotal());
        System.out.println("Discount: $" + order.getDiscount());
        System.out.println("Final Price: $" + order.getFinalPrice());
    }
}

