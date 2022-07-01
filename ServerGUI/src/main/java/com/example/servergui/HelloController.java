package com.example.servergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.servergui.Server.Counter;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private Label numberofuserslabel1;

public static double profit=0;
    public static ArrayList<SystemStatusHelper> arr=new ArrayList<>();
    public static ArrayList<ClientTransactionsHelper> Trr=new ArrayList<>();

    @FXML
    private Label numberofuserslabel;

   private Statement statement = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberofuserslabel1.setText(Counter+"");
        System.out.println(Counter);
        connectToDB();
        System.out.println("here");
        String s= "";
       // String s2="";
        //String s3="online";
        try {
            s = ""+queryfornumberofusers();
          //  s2=""+queryfornumberofonlineusers(s3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        numberofuserslabel.setText(s);

        //numberofonlineuserslabel.setText(s2);


    } @FXML
    public void ClientStatusbutton(ActionEvent event) throws IOException, SQLException {
        queryforadminUsers();
        Parent root = FXMLLoader.load(getClass().getResource("ClientTransactions.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void refreshbutton(ActionEvent event) throws IOException {
       // Counter=2;
        System.out.println(Counter);
        numberofuserslabel1.setText(Counter+"");

        System.out.println("here");
        String s= "";
        // String s2="";
        //String s3="online";
        try {
            s = ""+queryfornumberofusers();
            //  s2=""+queryfornumberofonlineusers(s3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        numberofuserslabel.setText(s);

    }

    @FXML
    public void SystemStatusbutton(ActionEvent event) throws IOException, SQLException {

        queryforadmin();
        String p=queryforProfit();
        profit=parseProfit(p);
        Parent root = FXMLLoader.load(getClass().getResource("SystemStatus.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    String quantityOfItems="";
    String priceOfItem="";
    double Doublequantity=0;
    double DoublePrice=0;
    double totalProfit=0;


    public double parseProfit(String s) {
        int i = 0;
        while (i < s.length()) {
            i++;
            while (s.charAt(i) != ' ') {
                quantityOfItems += s.charAt(i);
                i++;
            }

            Doublequantity=Double.parseDouble(quantityOfItems);
            quantityOfItems="";
            System.out.println(Doublequantity);
            i++;
            while (s.charAt(i) != ' ' && s.charAt(i) != '\0') {
                priceOfItem += s.charAt(i);
                i++;
                if (i == s.length()) {
                    break;
                }
            }
            DoublePrice=Double.parseDouble(priceOfItem);
            System.out.println(DoublePrice);
            priceOfItem="";
            totalProfit+=(DoublePrice*Doublequantity);
            Doublequantity=0;
            DoublePrice=0;
            System.out.println(totalProfit);

        }
        return totalProfit;
    }
    int queryfornumberofusers() throws SQLException {
        String s = "";
        int c=0;
        ArrayList<String> data = new ArrayList<>();
        String sql = "Select email from client ";
        System.out.println("");
        try {
            ResultSet resultSet = null;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                data.add(resultSet.getString("email"));

c++;

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(s);
        return c;
    }
    int queryfornumberofonlineusers(String online) throws SQLException {
        String s = "";
        int c=0;
        ArrayList<String> data = new ArrayList<>();
        String sql = "Select email from client where clientStatus=\""+online+"\"";
        System.out.println("Select email from client where online=clientStatus");
        try {
            ResultSet resultSet = null;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                data.add(resultSet.getString("email"));

                c++;

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(s);
        return c;
    }
    String queryforProfit() throws SQLException {
        String s = "";
        ArrayList<String> data = new ArrayList<>();
        String sql = "Select h.quantity,i.Price from has as h,item as i where h.itemname=i.ItemName";
        System.out.println("Select h.quantity,i.Price from has as h,item as i where h.itemname=i.ItemName");
        try {
            ResultSet resultSet = null;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                data.add(resultSet.getString("quantity"));
                data.add(resultSet.getString("Price"));


            }

            for (int i = 0; i < data.size(); ++i) {
                s = s + " " + data.get(i);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(s);
        return s;
    }
    void queryforadmin() throws SQLException {


        String sql = "Select * from item Order by Category";
        System.out.println("Select * from item order by Category");
        try {
            ResultSet resultSet = null;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                SystemStatusHelper s = new SystemStatusHelper();
                s.ItemName=resultSet.getString("ItemName");
                s.ItemId=(resultSet.getString("ItemId"));
                s.Price=(resultSet.getString("Price"));
                s.Category=(resultSet.getString("Category"));
                s.Quantity=(resultSet.getString("Quantity"));

                arr.add(s);}
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    void queryforadminUsers() throws SQLException {


        String sql = "Select c.Email,h.itemname,h.quantity,o.reqdate from client as c,has as h,o_order as o where c.Email=o.Email and o.orderid=h.orderid order by c.Email";
        System.out.println("Select * from item order by Category");
        try {
            ResultSet resultSet = null;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ClientTransactionsHelper s = new ClientTransactionsHelper();
                s.email=resultSet.getString("Email");
                s.itemname=(resultSet.getString("itemname"));
                s.quantity=(resultSet.getString("quantity"));
                s.reqdate=(resultSet.getString("reqdate"));


                Trr.add(s);}
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    void connectToDB() {
        try {
            Connection connect = null;
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Store";
            String name = "root";
            String pass = "fares123";
            connect = DriverManager.getConnection(url, name, pass);
            statement = connect.createStatement();

        } catch (Exception e) {

        }
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}