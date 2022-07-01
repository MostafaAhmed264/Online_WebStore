package com.example.clientgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.clientgui.HelloApplication.clientConnection;
import static com.example.clientgui.HelloController.c;

public class MyProfileController implements Initializable {

    @FXML
    Label fname;
    @FXML
    Label lname;
    @FXML
    Label email;
    @FXML
    Label password;
    @FXML
    Label phone;
    @FXML
    Label address;
    @FXML
    Label balance;
    @FXML
    Label fnamevalue;
    @FXML
    Label lnamevalue;
    @FXML
    Label emailvalue;
    @FXML
    Label passwordvalue;
    @FXML
    Label phonevalue;
    @FXML
    Label addressvalue;
    @FXML
    Label balancevalue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        fname.setText("first name:");
        lname.setText("Last name:");
        email.setText("email:");
        password.setText("password:");


        phone.setText("phone number:");
        address.setText("address:");
        balance.setText("balance:");

        fnamevalue.setText(c.getFname());
        lnamevalue.setText(c.getLname());
        emailvalue.setText(c.getEmail());
        passwordvalue.setText(c.getPassword());


        phonevalue.setText(c.getPhonenumber());
        addressvalue.setText(c.getAddress());
        balancevalue.setText(c.getbalance());

    }



    @FXML
    public void returnbutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}