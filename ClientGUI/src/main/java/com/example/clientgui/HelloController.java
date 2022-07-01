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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.clientgui.HelloApplication.clientConnection;

public class HelloController {
 public  static  Client c=new Client();
 public static ArrayList<Item> initialItems = new ArrayList<>();
    @FXML
    TextField t1 ;
    @FXML
    TextField t2 ;

String emailDB="";
String passwordDB = "";
@FXML
public void initialize() {
    try {
        String itemData = "";
        for (int i = 1; i <= 5; ++i) {
            itemData += clientConnection.sendRecieve("ISelect * from item where ItemId =\"" + i + "\"");
        }
        System.out.println(itemData);
        initialItems = Item.parseItems(itemData);
    }catch (Exception e)
    {
        e.printStackTrace();
    }
}
    @FXML
    public void loginbutton(ActionEvent event) throws IOException {
        String emailUser = t1.getText();
        String passwordUser = t2.getText();
        String clientData = clientConnection.sendRecieve(clientConnection.constructQueryClient(emailUser));
        if(clientData.length()!=0){


        int i = 1;
        while(clientData.charAt(i) != ' ')
        {
            emailDB += clientData.charAt(i);
            i++;
        }
        i++;
        while (clientData.charAt(i) != ' ')
        {
            if(clientData.charAt(i) != '\0') {
                passwordDB += clientData.charAt(i);
            }
            i++;
        }
System.out.println(passwordDB);

         if(!c.login(emailUser,passwordUser,emailDB,passwordDB)){
             Alert errorAlert = new Alert(Alert.AlertType.ERROR);
             errorAlert.setHeaderText("error");
             errorAlert.setContentText("Invalid email or password");
             errorAlert.showAndWait();
        //error
        }else {

c.parse(clientData);
             Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             Scene scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
         }
        }else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("error");
            errorAlert.setContentText("enter Valid email");
            errorAlert.showAndWait();
        }


    }
    @FXML
    public void registerbutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    }
