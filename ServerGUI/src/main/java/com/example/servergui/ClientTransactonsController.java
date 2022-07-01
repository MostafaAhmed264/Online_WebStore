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
import java.sql.SQLException;

public class ClientTransactonsController {
    @FXML
    private TableView<ClientTransactionsHelper> ItemsTable;

    @FXML
    private TableColumn<ClientTransactionsHelper,String> emailColumn=new TableColumn<>();

    @FXML
    private TableColumn<ClientTransactionsHelper,String> itemnameColumn=new TableColumn<>();
    @FXML
    private TableColumn<ClientTransactionsHelper,String> quantityColumn=new TableColumn<>();
    @FXML
    private TableColumn<ClientTransactionsHelper,String> reqdateColumn=new TableColumn<>();



    ObservableList<ClientTransactionsHelper> Items;


    public void initialize()
    {

        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        itemnameColumn.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        reqdateColumn.setCellValueFactory(new PropertyValueFactory<>("reqdate"));
        Items=ItemsTable.getItems();



        Items.addAll(HelloController.Trr);


        ItemsTable.setItems(Items);

    }
    @FXML
    public void backButton(ActionEvent event) throws IOException, SQLException {
HelloController.Trr.clear();

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
