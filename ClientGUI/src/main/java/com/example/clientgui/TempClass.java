package com.example.clientgui;

public class TempClass {
    public String itemName;
    public double price;
    public int quantity;

    public TempClass(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
