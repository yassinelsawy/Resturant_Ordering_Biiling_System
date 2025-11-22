package observer;

import model.Order;

public class KitchenObserver implements OrderObserver {
    public void update(Order order) {
        System.out.println("Kitchen notified for Order #" + order.getId());
    }
}
