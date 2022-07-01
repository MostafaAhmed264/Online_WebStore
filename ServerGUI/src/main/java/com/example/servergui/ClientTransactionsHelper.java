package com.example.servergui;

import java.util.ArrayList;

public class ClientTransactionsHelper {
    String email;
    String itemname;
    String quantity;
    String reqdate;

    public String getQuantity() {
        return quantity;
    }

    public String getReqdate() {
        return reqdate;
    }

    public String getItemname() {
        return itemname;
    }

    ArrayList<ClientTransactionsHelper> Tr=new ArrayList<>();

    public String getEmail() {
        return email;
    }
}
