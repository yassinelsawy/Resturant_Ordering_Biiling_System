package service;

import controller.OrderController;

public class RestaurantService {
    private OrderController orderController;

    public RestaurantService(OrderController orderController) {
        this.orderController = orderController;
    }

    public void createOrder() {
        orderController.processOrder();
    }

    public void payOrder() {
        orderController.handlePayment();
    }

    public void showReceipt() {
        orderController.generateReceipt();
    }
}

