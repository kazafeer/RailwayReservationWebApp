package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserOperation {
    public String login(String email,String password)
    {
        String name="";
        try {
            DbConnector dbConnector = new DbConnector();
            Connection connection = dbConnector.connection();
            PreparedStatement preparedStatement=connection.prepareStatement("Select * from user_details where email=? and password=?");
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet =preparedStatement.executeQuery();

           if (resultSet.next())
           {
               name=resultSet.getString(2);
           }
           else
               name="";
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return name;
    }

    public void signup(String email,String name,String password)
    {
        try {
            DbConnector dbConnector = new DbConnector();
            Connection connection = dbConnector.connection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into user_details VALUES(?,?,?)");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
