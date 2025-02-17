package ReservationSystem;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DBConnection {
  private static final  String url="jdbc:mysql://localhost:3306/busreserv";
    private static final String userName="root";
    private static final String password="12345";

   public static Connection  getConnection(){

        try {
            return DriverManager.getConnection(url,userName,password);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
