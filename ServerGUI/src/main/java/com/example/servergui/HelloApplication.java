package com.example.servergui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Statement;

public class HelloApplication extends Application {
    //private Statement statement = null;
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
Server Thread=new Server();
Thread.start();
        launch();

    }
}