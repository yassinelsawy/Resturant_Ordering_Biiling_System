package model;

import java.util.*;

public class Order {
    private static int counter = 1;
    private int id;
    private List<MenuItem> items;
    private double totalPrice;
    private double discount;
    private double finalPrice;

    public Order() {
        this.id = counter++;
        this.items = new ArrayList<>();
        this.totalPrice = 0;
        this.discount = 0;
        this.finalPrice = 0;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public double calculateTotal() {
        totalPrice = 0;
        for (MenuItem item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    public void applyDiscount(double discount) {
        this.discount = discount;
    }

    public void setFinalPrice(double price) {
        this.finalPrice = price;
    }

    public int getId() { return id; }
    public List<MenuItem> getItems() { return items; }
    public double getDiscount() { return discount; }
    public double getFinalPrice() { return finalPrice; }
}

