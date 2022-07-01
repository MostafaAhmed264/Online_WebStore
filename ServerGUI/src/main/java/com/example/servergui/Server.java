package com.example.servergui;

import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server extends Thread {
  public static  int Counter =0;
    Server() {
    }
@Override
public void run() {


        String s = new String();
        ServerSocket server = null;

        try {
            server = new ServerSocket(5000);
            server.setReuseAddress(true);

            while (true) {
                System.out.println("innn");

                Socket client = server.accept();


                System.out.println("New client connected" + client.getInetAddress().getHostAddress());
                ClientHandler clientSock = new ClientHandler(client, s);
                (new Thread(clientSock)).start();

            }
        } catch (IOException var12) {
            var12.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException var11) {
                    var11.printStackTrace();
                }
            }

        }

    }
}


