package com.example.clientgui;

import java.util.ArrayList;

public class Item {
    private String name;
    private String id;
    private double price;
    private int stock;
    private String category;



    public String getCategory() {
        return category;
    }

    public Item(String name, String id, double price, int stock, String category) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public static ArrayList<Item> parseItems (String x){
        ArrayList<Item> arry = new ArrayList<Item>();
      String in = "";
      String idd = "";
      String p = "";
      String c = "";
        String s = "";
      boolean nameflag = true;
      boolean idflag = false;
      boolean priceflag = false;
      boolean categoryflag = false;
      boolean stockflag = false;
      boolean ready = false;
        int i = 1;
      while(i<x.length()){
            if  (nameflag){
                in += x.charAt(i);
                i++;
                if (x.charAt(i) == ' '){
                i ++;
                nameflag = false;
                idflag = true;
                }
            }
            else if(idflag){
                idd += x.charAt(i);
                i++;
                if (x.charAt(i) == ' '){
                    i++;
                    idflag = false;
                    priceflag = true;
                }
            }
          else if(priceflag){
                p += x.charAt(i);
                i++;
                if (x.charAt(i) == ' '){
                    i++;
                    priceflag = false;
                    categoryflag = true;
                }
            }
            else if(categoryflag){
                c += x.charAt(i);
                i++;
                if (x.charAt(i) == ' '){
                    i++;
                    categoryflag = false;
                    stockflag = true;
                }
            }
            else if(stockflag){
                s += x.charAt(i);
                i++;
               if (i<x.length()-1) {
                   if (x.charAt(i) == ' ') {
                       i++;
                       stockflag = false;
                       nameflag = true;
                       ready = true;
                   }
               }
               else if (i == x.length())
                   ready = true;
            }
            if (ready)
            {
               arry.add(new Item(in,idd,Double.parseDouble(p),Integer.parseInt(s),c));
                in = "";
                idd = "";
                p = "";
                c = "";
                s = "";
                ready = false;
            }
        }

        return arry;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
