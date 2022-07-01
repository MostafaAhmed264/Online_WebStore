package com.example.servergui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SystemStatusController {
    @FXML
    private TableView<SystemStatusHelper> ItemsTable;

    @FXML
    private TableColumn<SystemStatusHelper,String> nameColumn=new TableColumn<>();

    @FXML
    private TableColumn<SystemStatusHelper,String> priceColumn=new TableColumn<>();
    @FXML
    private TableColumn<SystemStatusHelper,String> quantityColumn=new TableColumn<>();
    @FXML
    private TableColumn<SystemStatusHelper,String> idColumn=new TableColumn<>();
    @FXML
    private TableColumn<SystemStatusHelper,String> categoryColumn=new TableColumn<>();



    @FXML
    private Label ProfitLabel;
    ObservableList<SystemStatusHelper> Items;


    public void initialize()
    {
        ProfitLabel.setText(HelloController.profit+"");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ItemId"));
        Items=ItemsTable.getItems();



        Items.addAll(HelloController.arr);


        ItemsTable.setItems(Items);

    }
    @FXML
    public void backButton(ActionEvent event) throws IOException, SQLException {
HelloController.arr.clear();

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
