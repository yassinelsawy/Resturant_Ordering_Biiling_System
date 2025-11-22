package observer;

import model.Order;

public class WaiterObserver implements OrderObserver {
    public void update(Order order) {
        System.out.println("Waiter notified for Order #" + order.getId());
    }
}
