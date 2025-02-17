package traveller;
import java.sql.*;
import java.util.*;

public class Booking {
    public static Scanner sc=new Scanner(System.in);
    public static int view_seats(int bus_id,int tripid) throws SQLException {
        String query="";

         query="select capacity from bus where bus_id="+bus_id;


        Connection con=DBConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        int capacity=0;
        if (rs.next()) {

             capacity = rs.getInt("capacity");
            System.out.println("Bus Capacity: " + capacity);
        }
        if(tripid==0)
        query="select sum(tickets) total from booking where bus_id="+bus_id;
         else
            query="select sum(tickets) total from booking where bus_id="+bus_id+" and tripid="+tripid;

        rs=st.executeQuery(query);
        int booked=0;
        if (rs.next()) {

            booked = rs.getInt("total");

        }


        return capacity-booked;
    }

    public static int view_bus(int tripId) throws SQLException {

        String query="select bus_id from setc where tripid="+tripId;
        Connection con=DBConnection.getConnection();
        Statement st=con.createStatement();

        ResultSet rs=st.executeQuery(query);

        int bus_id=0;
        if(rs.next()){
         bus_id=rs.getInt("bus_id");}
        int avail_capacity=Booking.view_seats(bus_id,tripId);
        return avail_capacity;





    }
    public static void cancel_tickets() throws SQLException {
        System.out.println("Enter The Booking Id");
        int booking_id=sc.nextInt();
        Connection con=DBConnection.getConnection();
        String query="Delete from booking where booking_id="+booking_id;
        Statement st= con.createStatement();
        st.executeUpdate(query);
        System.out.println("Ticket has been Cancelled Successfully");


    }







}
