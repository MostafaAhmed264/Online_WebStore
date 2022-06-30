package com.example.clientgui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.sql.*;


import static com.example.clientgui.HelloApplication.clientConnection;
import static com.example.clientgui.HelloController.*;


public class mainpageController {
    @FXML
    private Label item1;
    @FXML
    private MenuItem menuitem1;
    @FXML
    private MenuButton menuButton;

    @FXML
    private Label balanceLabel;
    @FXML
    private Label item2;

    @FXML
    private Label item3;

    @FXML
    private Label item4;

    @FXML
    private Label item5;

    @FXML
    private Label price1;

    @FXML
    private Label price2;

    @FXML
    private Label price3;

    @FXML
    private Label price4;

    @FXML
    private Label price5;

    @FXML
    private Label notFound
            ;
    @FXML
    private Spinner<Integer> q1;

    @FXML
    private Spinner<Integer> q2;

    @FXML
    private Spinner<Integer> q3;

    @FXML
    private Spinner<Integer> q4;

    @FXML
    private Spinner<Integer> q5;
    @FXML
    private TextField searchBox;
    @FXML
    private ImageView sadPig;
    @FXML
    private Button prevBtn;
    @FXML
    private Button nextBtn;


    private ArrayList<Boolean> pageFlags = new ArrayList<>();
    private boolean flagFirst = true;
    private boolean flag = true;
    private static int indx = 0;
    private String searchString = new String("");
    private int totalCount = 0;
    private int remaining = 0;
    private int dbIndx = 0;
    private boolean Query = true;
    private int pageNumber = 0;
    ArrayList<Label> itemLabels = new ArrayList<>();
    ArrayList<Label> priceLabels = new ArrayList<>();
    ArrayList<Spinner> spinners = new ArrayList<>();
    public static ArrayList<ArrayList<Item>> items = new ArrayList<>();
    boolean search = false;


    @FXML
    public void initialize() throws IOException {

        balanceLabel.setText(c.getbalance());
        if(flagFirst)
        {
            itemLabels.add(item1);
            itemLabels.add(item2);
            itemLabels.add(item3);
            itemLabels.add(item4);
            itemLabels.add(item5);

            priceLabels.add(price1);
            priceLabels.add(price2);
            priceLabels.add(price3);
            priceLabels.add(price4);
            priceLabels.add(price5);

            spinners.add(q1);
            spinners.add(q2);
            spinners.add(q3);
            spinners.add(q4);
            spinners.add(q5);

            flagFirst = false;
            for(int i = 0; i < priceLabels.size();++i)
            {
                itemLabels.get(i).setText(initialItems.get(i).getName());
                priceLabels.get(i).setText(initialItems.get(i).getPrice()+"");
            }
        }
        if(search) {
            if (flag && !searchString.equals("")) {
                totalCount = Integer.parseInt(clientConnection.sendRecieve("NSelect distinct Count(ItemName) from item where ItemName like \"%" + searchString + "%\" or Category like \"%" + searchString + "%\"").substring(1));
                if (totalCount==0){
                    notFound.setVisible(true);
                    InputStream stream = new FileInputStream("sadPig.PNG");
                    Image image = new Image(stream);
                    sadPig.setImage(image);
                    sadPig.setVisible(true);
                    return;
                }
                flag = false;
                for (int i = 0; i < Math.ceil((totalCount / 5.0)); i++) {
                    pageFlags.add(true);
                    items.add(new ArrayList<Item>());
                }
            }

            if (!searchString.equals("") && totalCount != 0 && pageFlags.get(pageNumber)) {
                pageFlags.set(pageNumber, false);
                items.get(pageNumber).addAll(Item.parseItems(clientConnection.sendRecieve("ISelect distinct * from item  where ItemName like \"%" + searchString + "%\" or Category like \"%" + searchString + "%\"Limit " + (dbIndx) + ',' + '5')));
            }

            for (int i = 0; i < items.get(pageNumber).size(); ++i) {
                itemLabels.get(i).setVisible(true);
                priceLabels.get(i).setVisible(true);
                spinners.get(i).setVisible(true);
                itemLabels.get(i).setText(items.get(pageNumber).get(i).getName());
                priceLabels.get(i).setText(items.get((pageNumber)).get(i).getPrice() + "");
            }
            if(items.get(pageNumber).size() < 5 || items.size() == 1)
            {
                nextBtn.setDisable(true);
            }
            else{
                nextBtn.setDisable(false);
            }

        }

        if(items.size() == 0)
        {
            nextBtn.setDisable(true);
        }

        if(pageNumber == 0)
        {
            prevBtn.setDisable(true);
        }
        else{
            prevBtn.setDisable(false);
        }



        SpinnerValueFactory<Integer> valueFactory1 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);

        SpinnerValueFactory<Integer> valueFactory2 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);

        SpinnerValueFactory<Integer> valueFactory3 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);

        SpinnerValueFactory<Integer> valueFactory4 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);

        SpinnerValueFactory<Integer> valueFactory5 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);

        q1.setValueFactory(valueFactory1);
        q2.setValueFactory(valueFactory2);
        q3.setValueFactory(valueFactory3);
        q4.setValueFactory(valueFactory4);
        q5.setValueFactory(valueFactory5);

    }
    client fadiola
    mainpage tawfik aymona
    ClientConnection tfatef
    CartController abbas





    @FXML
    public void nextPagebutton(ActionEvent event) throws IOException {
        for (int i =0;i<itemLabels.size();i++){
            itemLabels.get(i).setVisible(false);
            priceLabels.get(i).setVisible(false);
            spinners.get(i).setVisible(false);
        }
        pageNumber++;
        if (pageFlags.get(pageNumber))
        {
            dbIndx += 5;
        }
        initialize();
    }

    @FXML
    public void prevPageButton(ActionEvent event) throws IOException
    {
        for (int i =0;i<itemLabels.size();i++)
        {
            itemLabels.get(i).setVisible(false);
            priceLabels.get(i).setVisible(false);
            spinners.get(i).setVisible(false);
        }
        pageNumber--;
        initialize();
    }
    @FXML
    public void MyProfilebutton(ActionEvent event) throws IOException {
        flag = true;
        search = true;
        searchString = searchBox.getText();
        searchBox.clear();
        items.clear();
        pageFlags.clear();
        sadPig.setVisible(false);
        notFound.setVisible(false);
        dbIndx = 0;
        for (int i =0;i<itemLabels.size();i++)
        {
            itemLabels.get(i).setVisible(false);
            priceLabels.get(i).setVisible(false);
            spinners.get(i).setVisible(false);
        }
        pageNumber=0;
        Parent root = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
        Stage stage = (Stage) menuButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void  addToCart(ActionEvent event) throws IOException {
        if(q1.getValue() != 0){
            c.getCart().addItem(items.get(pageNumber).get(0),q1.getValue());
        }

        if(q2.getValue() != 0){
            c.getCart().addItem(items.get(pageNumber).get(1),q2.getValue());
        }

        if(q3.getValue() != 0){
            c.getCart().addItem(items.get(pageNumber).get(2),q3.getValue());
        }

        if(q4.getValue() != 0){
            c.getCart().addItem(items.get(pageNumber).get(3),q4.getValue());
        }

        if(q5.getValue() != 0){
            c.getCart().addItem(items.get(pageNumber).get(4),q5.getValue());
        }
        c.getCart().printItems();
        c.getCart().printQuantity();

        Cartbutton(event);
    }
    @FXML
    public void Cartbutton(ActionEvent event) throws IOException {
        flag = true;
        search = true;
        searchString = searchBox.getText();
        searchBox.clear();
        items.clear();
        pageFlags.clear();
        sadPig.setVisible(false);
        notFound.setVisible(false);
        dbIndx = 0;
        for (int i =0;i<itemLabels.size();i++)
        {
            itemLabels.get(i).setVisible(false);
            priceLabels.get(i).setVisible(false);
            spinners.get(i).setVisible(false);
        }
        pageNumber=0;
        Parent root = FXMLLoader.load(getClass().getResource("Cart.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Transactionsbutton(ActionEvent event) throws IOException {
        flag = true;
        search = true;
        searchString = searchBox.getText();
        searchBox.clear();
        items.clear();
        pageFlags.clear();
        sadPig.setVisible(false);
        notFound.setVisible(false);
        dbIndx = 0;
        for (int i =0;i<itemLabels.size();i++)
        {
            itemLabels.get(i).setVisible(false);
            priceLabels.get(i).setVisible(false);
            spinners.get(i).setVisible(false);
        }
        pageNumber=0;
        String clientData = clientConnection.sendRecieve(clientConnection.constructQueryBuy(c.getEmail()));
        System.out.println(clientData);
        System.out.println(clientData.length());
        c.ParseTransactions(clientData);
        //c.tempFunction();
        for(int i=0;i<c.Trans.Transactions.size();i++){
            System.out.println(c.Trans.Transactions.get(i).getItemName());
            System.out.println(c.Trans.Transactions.get(i).getDate());
            System.out.println(c.Trans.Transactions.get(i).getPrice());
            System.out.println(c.Trans.Transactions.get(i).getQuantity());
        }
        Parent root = FXMLLoader.load(getClass().getResource("Transactions.fxml"));
        Stage stage = (Stage) menuButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void addToYourBalancebutton(ActionEvent event) throws IOException {
        flag = true;
        search = true;
        searchString = searchBox.getText();
        searchBox.clear();
        items.clear();
        pageFlags.clear();
        sadPig.setVisible(false);
        notFound.setVisible(false);
        dbIndx = 0;
        for (int i =0;i<itemLabels.size();i++)
        {
            itemLabels.get(i).setVisible(false);
            priceLabels.get(i).setVisible(false);
            spinners.get(i).setVisible(false);
        }
        pageNumber=0;
        Parent root = FXMLLoader.load(getClass().getResource("Cash.fxml"));
        Stage stage = (Stage) menuButton.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logoutbutton(ActionEvent event) throws IOException {
        flag = true;
        search = true;
        searchString = searchBox.getText();
        searchBox.clear();
        items.clear();
        pageFlags.clear();
        sadPig.setVisible(false);
        notFound.setVisible(false);
        dbIndx = 0;
        for (int i =0;i<itemLabels.size();i++)
        {
            itemLabels.get(i).setVisible(false);
            priceLabels.get(i).setVisible(false);
            spinners.get(i).setVisible(false);
        }
        pageNumber=0;
        c=null;
        c=new Client();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) menuButton.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void searchItem(ActionEvent event) throws IOException {
        flag = true;
        search = true;
        searchString = searchBox.getText();
        searchBox.clear();
        items.clear();
        pageFlags.clear();
        sadPig.setVisible(false);
        notFound.setVisible(false);
        dbIndx = 0;
        for (int i =0;i<itemLabels.size();i++)
        {
            itemLabels.get(i).setVisible(false);
            priceLabels.get(i).setVisible(false);
            spinners.get(i).setVisible(false);
        }
        pageNumber=0;
        initialize();
    }



}