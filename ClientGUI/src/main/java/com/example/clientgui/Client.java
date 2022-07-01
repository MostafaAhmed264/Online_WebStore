package com.example.clientgui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import static com.example.clientgui.HelloApplication.clientConnection;
import static com.example.clientgui.HelloController.c;

public class Client {
    private String itemname="";
    private String date="";
    private String Price="";
    TransactionsHelper Trans=new TransactionsHelper();
    String temp="";
    String quantity="";
    private String fname="";
    private String lname="";
    private String email="";
    private String password="";
    private String phonenumber="";
    private double balance=0;
    private String address="";
    public static Cart cart=new Cart();
    public static ArrayList<Order> orders = new ArrayList<>();


    public Cart getCart() {return cart;}

    public void setCart(Cart cart) {this.cart = cart;}
    public String getbalance(){
        return ""+balance;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }


    public String getAddress() {
        return address;
    }

    public void createAccount(String f, String l, String e, String p, String pn, Double b , String ad){
        fname = f;
        lname = l;
        email = e;
        password = p;
        phonenumber = pn;
        balance = b;
        address = ad;
        //send to server
    }

    public void ParseTransactions(String s){

        int i=0;
        while(i<s.length()) {
            i++;
            while (s.charAt(i) != ' ') {
                itemname += s.charAt(i);
                i++;
            }
            System.out.println(itemname);
            i++;
            while (s.charAt(i) != ' ') {
                quantity += s.charAt(i);
                i++;
            }
            System.out.println(quantity);
            i++;
            while (s.charAt(i) != ' ') {
                date += s.charAt(i);
                i++;
            }
            System.out.println(date);
            i++;
            while (s.charAt(i) != ' '  &&   s.charAt(i) !='\0') {
                Price += s.charAt(i);
                i++;
                if(i==s.length()){
                    break;
                }
            }
            System.out.println(Price);

            TransactionsHelper t=new TransactionsHelper();
            t.itemName=itemname;
            t.date=date;
            t.price=Price;
            t.quantity=quantity;
            Trans.Transactions.add(t);
            itemname="";
            date="";
            Price="";
            quantity="";

        }
    }

    public void parse(String s){
        int i=1;

        while(s.charAt(i) != ' '){
            email+=s.charAt(i);
            i++;
        }
        System.out.println(email);
        i++;
        while(s.charAt(i) != ' '){
            password+=s.charAt(i);
            i++;
        }
        System.out.println(password);
        i++;
        while(s.charAt(i) != ' '){
            fname+=s.charAt(i);
            i++;
        }
        System.out.println(fname);
        i++;
        while(s.charAt(i) != ' '){
            lname+=s.charAt(i);
            i++;
        }
        System.out.println(lname);
        i++;
        while(s.charAt(i) != ' '){
            phonenumber+=s.charAt(i);
            i++;
        }
        System.out.println(phonenumber);
        i++;
        while(s.charAt(i) != ' '){
            address+=s.charAt(i);
            i++;
        }
        System.out.println(address);
        i++;

        while(i<s.length()){
            temp+=s.charAt(i);
            i++;
            System.out.println(temp);
        }
        balance=Double.parseDouble(temp);
        System.out.println(balance);
    }
    public boolean login (String e1 ,String p1, String e2, String p2){
        return p1.equals(p2);
    }

//    public void addItem (String name,String id,int price,int stock,int q){
//        cart.addItem(name,id,price,stock,q);
//    }
    public void editItem (String name,int quantity){
        cart.editItem(name,quantity);
    }
    public void deleteItem (String name){
        cart.deleteItem(name);
    }

    public double deposit (double b){
        balance += b;
        return balance;
    }

    public double getBalanceDouble() {
        return balance;
    }

    public String purchase () throws IOException {
        clientConnection.sendRecieve("Buying");
        ArrayList<Integer> currQuan = new ArrayList<>();


        LocalDate reqDate = LocalDate.now();
        String reqDateS = reqDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //check that the item is still in stock
        for(int i=0;i<cart.getItems().size();i++) {
            String clientData2 = clientConnection.sendRecieve(clientConnection.constructQueryQuantityItem(cart.getItems().get(i).getId()));
            int quantity = Integer.parseInt(clientData2.substring(1));
            if (quantity < cart.getQuantity().get(i)) {
                String f = "" + cart.getItems().get(i).getName() + "is out of stock";
                cart.deleteItem(cart.getItems().get(i).getName());
                return f;
            }
            else
            {
                currQuan.add(quantity);
            }
        }



        if (balance >= cart.calculateTotalPrice()) {
            balance -= cart.calculateTotalPrice();

            String orderID = clientConnection.sendRecieve(clientConnection.constructQueryOrderInsert(email, reqDateS));
            clientConnection.sendRecieve(clientConnection.constructQueryClientUpdate(balance, email));

            for (int i = 0; i < cart.getItems().size(); i++) {
                Order order = new Order(cart.getItems().get(i), currQuan.get(i));
                order.setOrderId(Integer.parseInt(orderID));
                orders.add(order);
                clientConnection.sendRecieve(clientConnection.constructQueryItemUpdate(currQuan.get(i) - cart.getQuantity().get(i), cart.getItems().get(i).getId()));
                clientConnection.sendRecieve(clientConnection.constructQueryHasInsert(orderID, cart.getItems().get(i).getName(), cart.getQuantity().get(i)));
                System.out.println("Successful Purchase, Thank you!");
            }
            clientConnection.sendRecieve("done");
        }
        else
        {
            return "Failed";
        }


        int size = cart.getItems().size();
        for(int i = 0; i < size;++i)
            cart.deleteItem(cart.getItems().get(0).getName());
        return "Successful Purchase, Thank you!";


    }


    String getPersonalInfo (){
        String x = new String ();
        x = "Name: " + fname + " " + lname + "\n" + "Email: " + email + "\n" + "Phone Number: "+ phonenumber +
                "\n" + "Address: " + address + "\n" + "Balance: " + balance;
        return x;
    }


    /*public Item searchItem (String name){
    }*/



}
