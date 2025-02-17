package ReservationSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookingDAO {
    public int getBookedCount(int busNo,Date date )throws SQLException {
        String query="select count(passenger_name)from booking where bus_no=? and travel_date=?";
                Connection con=DBConnection.getConnection();
        PreparedStatement pst=con.prepareStatement(query);
        java.sql.Date sqldate=new java.sql.Date(date.getTime());
        pst.setInt(1,busNo);
        pst.setDate(2,sqldate);
        ResultSet rs=pst.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    public void addBooking(Booking book)throws SQLException{
        String query="Insert into booking values(?,?,?)";
        Connection con=DBConnection.getConnection();
        PreparedStatement pst= con.prepareStatement(query);
        pst.setString(1,book.passenger_name);
        pst.setInt(2,book.busNo);
        java.sql.Date sqldate=new java.sql.Date(book.date.getTime());
        pst.setDate(3,sqldate);
        pst.executeUpdate();
    }
}
