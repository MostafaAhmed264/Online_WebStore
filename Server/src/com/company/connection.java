package com.company;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
public class connection {

    public static void main(String[] args) {

            Connection connect = null ;
            Statement statement = null ;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null ;

            try
            {

                Class.forName("com.mysql.jdbc.Driver");
                String url="jdbc:mysql://localhost:3306/sakila";
                String name="root";
                String pass="fares123";
                connect= DriverManager.getConnection(url,name,pass);
                statement=connect.createStatement();
                // Store image to the table
                PreparedStatement pstmt = connect.prepareStatement("update actor set image = ? ;");
//                File file = new File("E:\\DSC_7785");
//                InputStream inputImage = new FileInputStream(file);
//
//                pstmt.setString(1, "sample image");
//Inserting Blob type
                InputStream in = new FileInputStream("E:\\carr.jpg");
                pstmt.setBlob(1, in);
                pstmt.execute();

// Store image to the table cell
//                Blob blob = rs.getBlob(1);
//                ImageIcon imageIcon = new ImageIcon(
//                        blob.getBytes(1, (int)blob.length()));
                String sql = "SELECT * from actor " ;
//                String sql2 = "update item set ItemId=100 where ItemId =7";
//                String sql3 = "insert into client values (\"feso1@gmail.com\",\"12345\",\"Fares\",\"wafy\",\"011481234\",\"nasrocity\",2000)";

                resultSet = statement.executeQuery(sql);


                while (resultSet.next()) {
                    System.out.println(resultSet.getString("actor_id") + "\t" +
                            resultSet.getString("first_name")  + "\t" +
                            resultSet.getString("last_name") + "\t" +
                            resultSet.getString("last_update") +"\t" +
                            resultSet.getString("image")
                    );

                }
//                statement.executeUpdate(sql2);
//                resultSet = statement.executeQuery(sql);
//                while (resultSet.next()) {
//                    System.out.println(resultSet.getString("ItemName") + "\t" +
//                            resultSet.getString("ItemId")  + "\t" +
//                            resultSet.getString("Price") + "\t" +
//                            resultSet.getString("Category") +"\t" +
//                            resultSet.getString("Quantity")
//                    );
               // }
                connect.close();

                System.out.println("Connection closed");

            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
            }

    }
}
