# Restaurant Ordering & Billing System

Simple Java console application implementing Abstract Factory, Simple Factory, Decorator, Strategy, Observer and Template Method patterns.

## Prerequisites
- JDK 8 or newer installed
- Command-line (Windows PowerShell / CMD)
- Project sources under: `C:\Java Projects\Resturant_Ordering_Biiling_System\src`

## Build and run
1. Open a command prompt.
2. From project root run:
   - Compile:
     javac -d out -sourcepath "C:\Java Projects\Resturant_Ordering_Biiling_System\src" "C:\Java Projects\Resturant_Ordering_Biiling_System\src\Main.java"
   - Run:
     java -cp out Main
3. Alternatively, import the project into an IDE (IntelliJ/Eclipse) as a Java project and run `Main`.

Note: If packages are used inside `src`, make sure directory structure matches package declarations.

## Interactive flow (what the program asks)
- Select Menu Type: Vegetarian / NonVeg / Kids
- Select Category: Pizza / Burger / Drink (available options may differ per menu type)
- Choose concrete item(s)
- Optionally add add-ons (decorators) per item type
- Repeat to add more items
- Confirm order to apply discounts and notify observers
- Choose payment method (Cash / Card / Wallet)
- Generate and view receipt

## Example test cases and expected results

Test case 1 — Single Pizza with add-on
- Steps:
  1. Menu type: Vegetarian
  2. Category: Pizza
  3. Item: ItalianPizza — base price $8.00
  4. Add-on: ExtraCheese (+$1.50)
  5. Finish order
  6. Payment: Cash
- Assumptions (example discount rules used by the app):
  - PizzaDiscount = 10%
- Calculation:
  - Subtotal = 8.00 + 1.50 = 9.50
  - Discount = 9.50 * 10% = 0.95
  - Final = 9.50 - 0.95 = 8.55
- Expected:
  - Payment success -> Receipt shows subtotal 9.50, discount 0.95, final price 8.55

Test case 2 — Multiple items with mixed categories
- Steps:
  1. Menu type: NonVeg
  2. Pizza: EasternPizza ($9.00) with ExtraToppings (+$2.00)
  3. Burger: ClassicBurger ($6.00) with BaconTopping (+$1.75)
  4. Drink: Cola ($2.00) with Ice (+$0.25)
  5. Finish order
  6. Payment: Card
- Assumptions:
  - PizzaDiscount = 10%
  - BurgerDiscount = 5%
  - DrinkDiscount = 2%
- Calculation:
  - Pizza subtotal = 9.00 + 2.00 = 11.00, pizza discount = 1.10 -> net = 9.90
  - Burger subtotal = 6.00 + 1.75 = 7.75, burger discount = 0.3875 -> net ≈ 7.36
  - Drink subtotal = 2.00 + 0.25 = 2.25, drink discount = 0.045 -> net ≈ 2.21
  - Order subtotal = 11.00 + 7.75 + 2.25 = 21.00
  - Total discount ≈ 1.10 + 0.3875 + 0.045 = 1.5325
  - Final price ≈ 21.00 - 1.5325 = 19.4675 → round to 19.47
- Expected:
  - Card payment success -> Receipt shows breakdown, discounts and final ~19.47

Test case 3 — Vegetarian menu cannot produce non-veg burger
- Steps:
  1. Menu type: Vegetarian
  2. Try to select a non-veg burger (should not appear or should be blocked)
- Expected:
  - App prevents selection of non-veg burgers when Vegetarian menu is selected.

Test case 4 — Seasonal discount override
- Steps:
  1. Add any order (e.g., Pizza $10)
  2. If SeasonalDiscount strategy is active, assume SeasonalDiscount = 15%
- Calculation:
  - Subtotal 10.00, discount 1.50, final 8.50
- Expected:
  - Receipt reflects SeasonalDiscount applied (higher discount).

## Where to adjust discount/payment amounts (if needed)
- Discount percentages are defined in discount strategy classes (e.g., `PizzaDiscount`, `BurgerDiscount`, `DrinkDiscount`, `SeasonalDiscount`). Modify the calculation logic there to change rules.
- Payment handling is in `PaymentController` and concrete `PaymentStrategy` classes. Modify to simulate success/failure behaviour.

## Troubleshooting
- Compilation errors: ensure `src` tree mirrors package declarations and run javac with correct `-sourcepath` and `-d` output directory.
- Runtime errors: check stack trace, confirm all classes compiled into `out` directory.

## Notes
- The console UI is interactive — follow on-screen prompts.
- Receipts and logs are printed to console. Observers (Kitchen/Waiter) print notifications when orders are placed.


