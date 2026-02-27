# Restaurant Ordering & Billing System

A Java console application for placing restaurant orders, applying discounts, and processing payments. Built with four core design patterns: **Abstract Factory**, **Decorator**, **Strategy**, and **Observer**.

---

## How to Run

### Prerequisites

- JDK 8 or newer

### Option 1 — Command Line

```bash
# From the project root directory
javac -d out -sourcepath src src/Main.java
java -cp out Main
```

### Option 2 — IDE

Open the project in IntelliJ IDEA or Eclipse and run `Main.java`.

---

## Features

- **Menu types** — Vegetarian, Non-Veg, and Kids menus
- **Food categories** — Pizzas (Italian, Eastern), Burgers (Classic, Cheese), Drinks (Cola, Juice)
- **Customizable add-ons** — Extra cheese, extra sauce, extra toppings, bacon, ice, lemon, sugar
- **Automatic discounts** — 10% on pizzas, 8% on burgers, 5% on drinks (or no discount)
- **Multiple payment methods** — Cash, Card, or Wallet
- **Live notifications** — Kitchen and Waiter are notified when an order is placed
- **Receipt generation** — Displays item breakdown, discount, and final price

---

## UML Class Diagram

![UML Class Diagram](Restaurant.drawio.svg)

---

## Class Relationships

The system is organized into layered packages where each layer depends only on the layers below it:

- **`Main`** creates a **`UI`** instance and starts the console loop.
- **`UI`** owns a **`RestaurantService`**, which acts as a façade and delegates all work to **`OrderController`**.
- **`OrderController`** is the central orchestrator. It holds references to:
  - A **`MenuFactory`** (interface) — set at runtime to one of `VegetarianMenuFactory`, `NonVegMenuFactory`, or `KidsMenuFactory`. Each concrete factory creates the appropriate **`PizzaFactory`**, **`BurgerFactory`**, and **`DrinkFactory`** (simple factories that instantiate concrete `MenuItem` subclasses).
  - A **`PaymentController`** — which holds a **`PaymentStrategy`** (interface) and delegates payment to `CashPayment`, `CardPayment`, or `WalletPayment`.
  - A **`DiscountManager`** — which holds a **`DiscountStrategy`** (interface) and delegates discount calculation to `PizzaDiscount`, `BurgerDiscount`, `DrinkDiscount`, `NoDiscount`, or `SeasonalDiscount`.
  - An **`OrderPublisher`** (subject) — which maintains a list of **`OrderObserver`** subscribers (`KitchenObserver`, `WaiterObserver`) and notifies them when an order is placed.
  - An **`Order`** — which aggregates a list of **`MenuItem`** objects.
- **`MenuItem`** is the abstract base class. Concrete items (`ItalianPizza`, `EasternPizza`, `ClassicBurger`, `CheeseBurger`, `Cola`, `Juice`) extend it directly.
- **Decorators** (`PizzaDecorator`, `BurgerDecorator`, `DrinkDecorator`) also extend `MenuItem` and wrap another `MenuItem` instance, forming a recursive composition. Concrete decorators like `ExtraCheesePizza` or `BaconTopping` override `getPrice()` to add their cost on top of the wrapped item.

In short: `UI` → `RestaurantService` → `OrderController` → factories, strategies, observers, and the `Order`/`MenuItem` model.

---

## Class Design & Design Patterns

### 1. Abstract Factory Pattern

Creates families of related menu items depending on the selected menu type.

| Class | Role |
|---|---|
| `MenuFactory` | Abstract factory interface |
| `VegetarianMenuFactory` | Produces pizza & drink factories (no burgers) |
| `NonVegMenuFactory` | Produces pizza, burger & drink factories |
| `KidsMenuFactory` | Produces pizza, burger & drink factories |
| `PizzaFactory` | Simple factory — creates `ItalianPizza` or `EasternPizza` |
| `BurgerFactory` | Simple factory — creates `ClassicBurger` or `CheeseBurger` |
| `DrinkFactory` | Simple factory — creates `Cola` or `Juice` |

### 2. Decorator Pattern

Wraps a `MenuItem` to dynamically add extras without modifying the original object.

| Base Decorator | Concrete Decorators | Extra Cost |
|---|---|---|
| `PizzaDecorator` | `ExtraCheesePizza`, `ExtraSaucePizza`, `ExtraToppingsPizza` | +$1.0, +$0.5, +$1.5 |
| `BurgerDecorator` | `ExtraCheeseBurger`, `ExtraSauceBurger`, `BaconTopping` | +$0.8, +$0.5, +$1.2 |
| `DrinkDecorator` | `Ice`, `Lemon`, `Sugar` | +$0.2, +$0.3, +$0.1 |

All decorators extend `MenuItem`, wrapping an inner item and overriding `getName()` / `getPrice()`.

### 3. Strategy Pattern

Used in two places — **payments** and **discounts** — to swap algorithms at runtime.

**Payment strategies:**

| Interface | Implementations |
|---|---|
| `PaymentStrategy` | `CashPayment`, `CardPayment`, `WalletPayment` |

`PaymentController` holds a `PaymentStrategy` and delegates the `pay()` call to it.

**Discount strategies:**

| Interface | Implementations | Discount |
|---|---|---|
| `DiscountStrategy` | `PizzaDiscount` | 10% |
| | `BurgerDiscount` | 8% |
| | `DrinkDiscount` | 5% |
| | `NoDiscount` | 0% |
| | `SeasonalDiscount` | 15% |

`DiscountManager` holds a `DiscountStrategy` and applies it to an order.

### 4. Observer Pattern

Notifies interested parties whenever a new order is created.

| Class | Role |
|---|---|
| `OrderObserver` | Observer interface with `update(Order)` |
| `OrderPublisher` | Subject — maintains a list of observers and calls `notify()` |
| `KitchenObserver` | Prints a kitchen notification on new orders |
| `WaiterObserver` | Prints a waiter notification on new orders |

### Other Classes

| Class | Purpose |
|---|---|
| `MenuItem` | Abstract base class for all food/drink items (`name`, `price`) |
| `Order` | Holds a list of `MenuItem`s, tracks total, discount, and final price |
| `OrderController` | Orchestrates the full order flow (menu selection → add-ons → discount → notify) |
| `RestaurantService` | Service layer that delegates to `OrderController` |
| `UI` | Console interface — main menu loop (Create Order / Pay / Receipt / Exit) |
| `Main` | Entry point — launches the `UI` |

---

## Project Structure

```
src/
├── Main.java                  # Entry point
├── ui/UI.java                 # Console menu
├── service/RestaurantService.java
├── controller/OrderController.java
├── model/                     # MenuItem, Order, concrete items
├── factory/                   # Abstract Factory + Simple Factories
├── decorator/                 # Decorator base classes + add-ons
├── observer/                  # Observer interface + publisher + observers
└── strategy/                  # Payment & Discount strategies
```
