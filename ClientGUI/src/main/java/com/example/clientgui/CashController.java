package com.example.clientgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import static com.example.clientgui.HelloController.c;
import static com.example.clientgui.HelloApplication.clientConnection;
public class CashController {
    @FXML
    TextField amount ;
    @FXML
    public void backbutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addbutton(ActionEvent event) throws IOException {
        double newAmount= Double.parseDouble(amount.getText());
        c.deposit(newAmount);
        String clientData = clientConnection.sendRecieve(clientConnection.constructQueryClientUpdate(c.getBalanceDouble(),c.getEmail()));
        amount.clear();
        Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
        errorAlert.setHeaderText("Confirmation");
        errorAlert.setContentText("the amount has been added to your balance");
        errorAlert.showAndWait();
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}