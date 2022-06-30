package com.example.clientgui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Date;

public class ClientConnection {
    private Socket socket = null;

    ClientConnection (Socket socket)
    {
        this.socket = socket;
    }

    public String constructInsertQueryClient(String e,String p,String f,String l,String phone,String address,double balance )
    {
        return "CInsert into client values(\""+ e + "\"," +
                "\""+ p + "\"," +
                "\""+ f + "\"," +
                "\""+ l + "\"," +
                "\""+ phone + "\"," +
                "\""+ address + "\"," +
                "\""+ balance + "\")"  ;
    }
    //purchase
    public String constructQueryHasInsert(String orderId, String ItemName , int quantity)
    {
        return "HInsert into has values (\"" + orderId + "\""+",\""+ItemName+"\","+"\""+quantity +"\")";
    }
    //purchase
    public String constructQueryOrderInsert(String email , String reqDate)
    {
        return "OInsert into o_order values (\"" + '0' + "\""+",\""+email+"\","+"\""+reqDate +"\")";
    }
    public String constructQueryBuy(String s)
    {

        return "HSelect h.itemname,h.quantity,o.reqdate,i.Price from o_order as O,has as h,item as i where h.itemname=i.ItemName and h.orderid=o.orderid and email = \"" + s + "\"";
    }
    //purchase
    public String constructQueryClientUpdate(double s,String email)
    {
        return "CUpdate client set Balance= \"" + s + "\""+"where email=\"" + email + "\"";
    }
    //purchase
    public String constructQueryItemUpdate(double s,String id)
    {
        return "IUpdate item set Quantity= \"" + s + "\""+"where ItemId=\"" + id + "\"";
    }
    public String constructQueryClient(String s)
    {
        return "CSelect * from client where Email = \"" + s + "\"";
    }
    //Purhcase
    public String constructQueryQuantityItem(String s)
    {
        return "ISelect Quantity from item where ItemId = \"" + s + "\"";
    }

    public String sendRecieve(String s) throws IOException {
        // writing to server
        String recieved = "";
        PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);

        // reading from server
        BufferedReader in
                = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));

        String line = null;
        while (!"exit".equalsIgnoreCase(line)) {
            out.println(s);
            out.flush();
            recieved = in.readLine();
            System.out.println(recieved);
            line = "exit";
        }
        return recieved;
    }

}

