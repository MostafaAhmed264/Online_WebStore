package com.example.clientgui;

import com.example.clientgui.Item;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.*;

import static com.example.clientgui.HelloController.c;
public class CartController {
    @FXML
    private TableView<TempClass> cartTable;
    @FXML
    private TableColumn<TempClass,String> ItemNameColumn=new TableColumn<>();
    @FXML
    private TableColumn<TempClass,Integer> QuantityColumn=new TableColumn<>();
    @FXML
    private TableColumn<TempClass,Double> PriceColumn=new TableColumn<>();
    @FXML
    private Label totalPrice;

    public static ArrayList<TempClass> t = new ArrayList<>();


    ObservableList<TempClass> lItems;

    public void fillList(ArrayList<TempClass> t)
    {
        for (int i = 0; i < c.getCart().getItems().size(); ++i)
        {
            TempClass  tempClass = new TempClass(c.getCart().getItems().get(i).getName(),c.getCart().getItems().get(i).getPrice(),c.getCart().getQuantity().get(i));
            t.add(tempClass);
        }
    }
    public void initialize()
    {
        totalPrice.setText(c.getCart().calculateTotalPrice()+"");
        fillList(t);
        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ItemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        lItems = cartTable.getItems();
        lItems.addAll(t);
        cartTable.setItems(lItems);

    }
    @FXML
    public void returnbutton(ActionEvent event) throws IOException {
        t.clear();
        lItems.clear();
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void purchasebutton(ActionEvent event) throws IOException {
        //if else
        String str = c.purchase();
        if (str.equals("Successful Purchase, Thank you!")) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setHeaderText("confirmation");
            confirmation.setContentText("Successful Purchase, Thank you!");
            confirmation.showAndWait();
            returnbutton(event);
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("error");
            errorAlert.setContentText(str);
            errorAlert.showAndWait();
            c.getCart().emptyCart();
            returnbutton(event);
        }
    }

    @FXML
    public void editItem(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        int selectedID = cartTable.getSelectionModel().getSelectedIndex();
        TempClass tempClass = t.get(selectedID);
        EditQuantity.item = tempClass;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editQuantity.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("edit Quantity");
        stage.setScene(scene);
        stage.showAndWait();
        t.clear();
        lItems.clear();
        initialize();
    }
    @FXML
    public void deleteItem()
    {
        int selectedID = cartTable.getSelectionModel().getSelectedIndex();
        TempClass tempClass = t.get(selectedID);
        c.getCart().deleteItem(tempClass.getItemName());
        lItems.remove(selectedID);
        t.remove(selectedID);
        totalPrice.setText(c.getCart().calculateTotalPrice()+"");
    }

}