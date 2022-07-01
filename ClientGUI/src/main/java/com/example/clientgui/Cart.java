package com.example.clientgui;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Integer> quantity = new ArrayList<>();

    public void addItem(Item it,int q){
        for (int i=0; i<items.size();i++){
            String n = items.get(i).getName();
            if (it.getName().equals(n)){
                quantity.set(i,quantity.get(i)+q);
                return;
             }
        }

        items.add(it);
        quantity.add(q);

    }

    public void editItem(String name,int q) {
        if (q==0){
            deleteItem(name);
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            if (name.equals(items.get(i).getName())) {
                quantity.set(i, q);
            }
        }
    }
    public void deleteItem(String name){
        for (int i = 0; i < items.size(); i++) {
            if (name.equals(items.get(i).getName())) {
             quantity.remove(i);
             items.remove(i);
            }
        }
    }

   public double calculateTotalPrice (){
        double totalprice = 0;

        for (int i=0;i< items.size();i++){
            totalprice+= items.get(i).getPrice()* quantity.get(i);
        }

        return totalprice;

    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    public void emptyCart(){
        items.clear();
        quantity.clear();
    }

   public void printItems(){
        for (int i=0;i<items.size();i++)
            System.out.print(items.get(i).getName()+" ");
    }
   public void printQuantity(){
        System.out.print(quantity+" ");
    }

}
