package com.example.clientgui;

public class Order {
    private int orderId;
    private final Item item;
    private final int quantity;

    public Order(Item item, int quantity) {
        this.orderId = 0;
        this.item = item;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
