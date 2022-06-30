import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private Statement statement = null;
    static int orderId =0;
    final String s;

    // Constructor
    public ClientHandler(Socket socket, String s)
    {
        this.clientSocket = socket;
        this.s = s;
        System.out.println(s);

    }

    public void run()
    {
        PrintWriter out = null;
        BufferedReader in = null;
        try {

            // get the outputstream of client
            out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            // get the inputstream of client
            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));

            String line;
            connectToDB();

            while ((line = in.readLine()) != null) {
                ArrayList<String> data = new ArrayList<>();
                // writing the received message from
                // client
                System.out.printf(
                        " Sent from the client: %s\n",
                        line);
                String s = "";
                if(line.charAt(1) == 'S')
                    data = query(line);
                else if (line.charAt(1) == 'I')
                    s = insertquery(line.substring(1));
                else if (line.charAt(1)=='U')
                    s = insertquery(line.substring(1));
                else if (line.equals("Buying"))
                    s= synchBuy(out,in);
                for (int i =0;i<data.size();++i){
                    s = s + " " + data.get(i);
                }
                out.println(s);
                out.flush();
            }




        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    String synchBuy(PrintWriter out,BufferedReader in ){
        synchronized (s) {
            try {
                orderId = initializeOrder();
                System.out.println(orderId);
                orderId++;
                boolean flag = true;
                String line = "";
                out.println("Synched");
                out.flush();
                while (!(line = in.readLine()).equals("done")) {
                    ArrayList<String> data = new ArrayList<>();
                    System.out.println(line + " " + Thread.currentThread().getName());
                    String s = "";
                    if (line.charAt(1) == 'S')
                        data = queryQuantity(line.substring(1));
                    else if (line.charAt(1) == 'U')
                        s = insertquery(line.substring(1));
                    else if (line.charAt(1) == 'I') {
                        if (line.charAt(0) == 'O' && flag) {
                            flag = false;
                            line = line.replaceFirst("0", orderId + "");
                            if (insertquery(line.substring(1)).equals("valid")) {
                                s = orderId + "";
                            } else {
                                s = "invalid";
                            }
                        } else if (line.charAt(0) == 'H') {
                            s = insertquery(line.substring(1));
                        }
                    }

                    for (int i = 0; i < data.size(); ++i) {
                        s = s + " " + data.get(i);
                    }

                    out.println(s);
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "ok!";
        }
    }

    void connectToDB() {
        try {
            Connection connect = null;
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Store";
            String name = "root";
            String pass = "fares123";
            connect = DriverManager.getConnection(url, name, pass);
            statement = connect.createStatement();

        } catch (Exception e) {

        }


    }


      String insertquery (String sql)

    {
        synchronized (s) {
            try {
                System.out.println(sql);
                statement.executeUpdate(sql);
            } catch (SQLIntegrityConstraintViolationException e) {
                return "invalid";
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "valid";
        }
    }


    ArrayList<String> queryQuantity(String sql){
        ArrayList<String> data = new ArrayList<>();
        System.out.println(sql);
        try {
            ResultSet resultSet = null;
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                data.add(resultSet.getString("Quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;

    }

    int initializeOrder() throws SQLException {
        ResultSet  resultSet = statement.executeQuery("Select MAX(orderid) as o from o_order");
        resultSet.next();
        return Integer.parseInt(resultSet.getString("o"));
    }
    ArrayList<String> query(String sql) {
        ArrayList<String> data = new ArrayList<>();
        char flag = sql.charAt(0);
        sql = sql.substring(1);
        System.out.println(sql);
        try {
            ResultSet resultSet = null;
            resultSet = statement.executeQuery(sql);
            if(flag == 'C') {
                while (resultSet.next()) {
                    data.add(resultSet.getString("Email"));
                    data.add(resultSet.getString("pasw"));
                    data.add(resultSet.getString("fname"));
                    data.add(resultSet.getString("Lname"));
                    data.add(resultSet.getString("Phone"));
                    data.add(resultSet.getString("Address"));
                    data.add(resultSet.getString("Balance"));
                }
            }
            else if (flag == 'I')
            {
                while (resultSet.next()) {
                    data.add(resultSet.getString("ItemName"));
                    data.add(resultSet.getString("ItemId"));
                    data.add(resultSet.getString("Price"));
                    data.add(resultSet.getString("Category"));
                    data.add(resultSet.getString("Quantity"));
                }
            }
            else if (flag =='H'){
                while (resultSet.next()) {
                    data.add(resultSet.getString("ItemName"));
                    data.add(resultSet.getString("quantity"));
                    data.add(resultSet.getString("reqdate"));
                    data.add(resultSet.getString("price"));
                }
            }
            else if (flag == 'N')
            {
                while (resultSet.next()){
                    data.add(resultSet.getString("count(ItemName)"));
                }
            }
        return data;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return data;
    }
}