//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.sql.*;
//import java.util.ArrayList;
//
//
//
//public class Server {
//    private ServerSocket server = null;
//    private Socket socket = null;
//    public Server(int port) {
//        // starts server and waits for a connection
//        try {
//            server = new ServerSocket(port);
//            System.out.println("Server started");
//
//            while(true) {
//                System.out.println("Waiting for a client ...");
//                socket = server.accept();
//                ClientThread t = new ClientThread(socket);
//                socket = new Socket();
//                t.start();
//            }
//
//        }
//        catch (IOException i)
//        {
//            System.out.println(i);
//        }
//    }
//
//    public static void main(String[] args) {
//        Server server = new Server(5000);
//    }
//}


import com.sun.tools.attach.VirtualMachine;

import java.io.*;
import java.net.*;

// Server class
class Server {
    public static void main(String[] args)
    {
        String s = new String();
        ServerSocket server = null;

        try {

            // server is listening on port 1234
            server = new ServerSocket(5000);
            server.setReuseAddress(true);

            // running infinite loop for getting
            // client request
            while (true) {

                // socket object to receive incoming client
                // requests
                Socket client = server.accept();

                // Displaying that new client is connected
                // to server
                System.out.println("New client connected"
                        + client.getInetAddress()
                        .getHostAddress());

                // create a new thread object
                ClientHandler clientSock
                        = new ClientHandler(client,s);

                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}















