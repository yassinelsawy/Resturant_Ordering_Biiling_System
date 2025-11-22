package observer;

import model.Order;
import java.util.*;

public interface OrderObserver {
    void update(Order order);
}

