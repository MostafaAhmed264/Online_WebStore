package com.example.clientgui;

import java.util.ArrayList;

public class TransactionsHelper {
    String itemName;
    String date;
    String price;

    String quantity;
    ArrayList<TransactionsHelper> Transactions = new ArrayList<>();

    public String getItemName() {
        return itemName;
    }

    public ArrayList<TransactionsHelper> getTransactions() {
        return Transactions;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getQuantity() {
        return quantity;
    }
}