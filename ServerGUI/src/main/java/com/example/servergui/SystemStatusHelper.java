package com.example.servergui;

import java.util.ArrayList;

public class SystemStatusHelper {
    ArrayList<SystemStatusHelper> data=new ArrayList<>();
    String ItemName;
    String ItemId;
    String Price;
    String Category;
    String Quantity;

    public ArrayList<SystemStatusHelper> getData() {
        return data;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getCategory() {
        return Category;
    }

    public String getPrice() {
        return Price;
    }

    public String getItemId() {
        return ItemId;
    }

    public String getItemName() {
        return ItemName;
    }
}
