package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Booking {
    public boolean availability_check(String source,String destination,String trainno,String date)
    {
        try {
            DbConnector dbConnector = new DbConnector();
            Connection connection = dbConnector.connection();
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from train_avail where source=? and destination=? and trainno=? and date=?");
            preparedStatement.setString(1, source);
            preparedStatement.setString(2, destination);
            preparedStatement.setString(3, trainno);
            preparedStatement.setDate(4, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    public void bookingconfirm(String email,String bookingid,String source,String destination,String trainno,String status,String date)
    {
        try {
            DbConnector dbConnector = new DbConnector();
            Connection connection = dbConnector.connection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into user_bookings VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, bookingid);
            preparedStatement.setString(3, source);
            preparedStatement.setString(4, destination);
            preparedStatement.setString(5, trainno);
            preparedStatement.setString(6, status);
            preparedStatement.setDate(7, Date.valueOf(date));
            preparedStatement.executeUpdate();
            connection.close();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
