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

import static com.example.clientgui.HelloApplication.clientConnection;
import static com.example.clientgui.HelloController.c;

public class RegisterController {
    @FXML
    TextField t1 ;
    @FXML
    TextField t2 ;
    @FXML
    TextField t3 ;
    @FXML
    TextField t4 ;
    @FXML
    TextField t5 ;
    @FXML
    TextField t6 ;
    @FXML
    TextField t7 ;
    String fname;
    String lname;
    String address;
    String phone;
    String email;
    String password;
    Double balance;

    public void registerbutton(ActionEvent event) throws IOException {
        fname=t1.getText();
        lname=t2.getText();
        email=t3.getText();
        password=t4.getText();
        address=t6.getText();
        phone=t5.getText();
        balance=Double.parseDouble(t7.getText());

        String clientData = clientConnection.sendRecieve(clientConnection.constructInsertQueryClient(email,password,fname,lname,phone,address,balance));
        System.out.println(clientData);
        if(clientData.equals("valid")){
            c.createAccount(fname,lname,email,password,phone,balance,address);
            Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("error");
            errorAlert.setContentText("this email is already registered");
            errorAlert.showAndWait();
        }



    }
}
