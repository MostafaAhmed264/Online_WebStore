package com.example.clientgui;
import com.example.clientgui.Item;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import static com.example.clientgui.HelloController.c;

public class TransactionsController {

    @FXML
    private TableView<TransactionsHelper> TransactionsTable;

    @FXML
    private TableColumn<TransactionsHelper,String> ItemNameColumn=new TableColumn<>();

    @FXML
    private TableColumn<TransactionsHelper,String> PriceColumn=new TableColumn<>();
    @FXML
    private TableColumn<TransactionsHelper,String> dateColumn=new TableColumn<>();
    @FXML
    private TableColumn<TransactionsHelper,String> quantityColumn=new TableColumn<>();



    ObservableList<TransactionsHelper> Items;


    public void initialize()
    {

        ItemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        Items=TransactionsTable.getItems();



        Items.addAll(c.Trans.Transactions);


        TransactionsTable.setItems(Items);

    }
    @FXML
    public void backbutton(ActionEvent event) throws IOException {
        c.Trans.Transactions.clear();
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}