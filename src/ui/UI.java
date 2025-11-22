package ui;

import service.RestaurantService;
import controller.OrderController;
import java.util.Scanner;

public class UI {
    private RestaurantService restaurantService;
    private Scanner scanner;

    public UI() {
        this.restaurantService = new RestaurantService(new OrderController());
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("\n--- Restaurant Ordering System ---");
            System.out.println("1. Create Order");
            System.out.println("2. Pay Order");
            System.out.println("3. Show Receipt");
            System.out.println("4. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    handleCreateOrder();
                    break;
                case 2:
                    handlePayment();
                    break;
                case 3:
                    showReceipt();
                    break;
                case 4:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void handleCreateOrder() {
        restaurantService.createOrder();
    }

    public void handlePayment() {
        restaurantService.payOrder();
    }

    public void showReceipt() {
        restaurantService.showReceipt();
    }
}

