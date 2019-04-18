package model;


import java.sql.*;

public class DbConnector {
   public Connection conn;
    public Connection connection()
     {
         try {
             Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/railways", "root", "root");

         }
         catch (Exception e)
         {
         System.err.println(e);
         }
         return conn;
     }

}
