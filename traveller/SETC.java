package traveller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

public class SETC {
    static Scanner sc=new Scanner(System.in);
    public static String get_date(int bus_id) throws SQLException {
        Connection con=DBConnection.getConnection();
        String query="Select date from SETC where bus_id="+bus_id;
        Statement st= con.createStatement();
        ResultSet rs=st.executeQuery(query);
        if(rs.next()){
            return rs.getString(1);
        }
        return "";

    }
    public static void displaySetcInfo() throws SQLException {
        String query="select * from SETC";
        Connection con=DBConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        System.out.printf("%-8s %-18s %-18s %-5s %-15s %-10s %n",
                "Trip_id", "Boarding Point", "Destination", "Cost", "Date", "Boarding Time");
        System.out.println("**********************************************************************************");
        while(rs.next()){
            int tripId = rs.getInt("tripid");
            String boardPoint=rs.getString("boardingPoint");
            String destination=rs.getString("Destination");
            int cost = rs.getInt("cost");
            String date= String.valueOf(rs.getDate("date"));
            String time=String.valueOf(rs.getTime("travel_time"));
            System.out.printf("%-8s %-18s %-18s %-5s %-15s %-10s %n",
                    tripId, boardPoint, destination, cost, date, time);

        }
        System.out.println("**********************************************************************************");
        boolean bpflag=true;
        boolean dpflag=true;
        do {
            System.out.print("Enter the Boarding Point: ");
            String bp = sc.nextLine().toUpperCase();
            System.out.print("Enter the Destination Point: ");
            String dp = sc.nextLine().toUpperCase();
            // Prepare the patterns for the LIKE operator
            String bpm = bp.substring(0, 3) + "%";
            String dpm = dp.substring(0, 3) + "%";

// Modify the query to use LIKE for the first three characters
            query = "SELECT * FROM SETC WHERE boardingPoint LIKE ? AND Destination LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, bpm);
            pstmt.setString(2, dpm);
            rs = pstmt.executeQuery();


            if (rs.next()) {
                System.out.println();
                System.out.println("Buses  found:");

                SETC.displayAvailInfo(rs);

                bpflag=false;
            } else {
                System.out.println("No buses found from " + bp + " to " + dp + ". (OR)");
                System.out.println("Enter Valid Input " + bp + " to " + dp + ".");
            }



        } while (bpflag && dpflag);




    }


    public static void displayAvailInfo(ResultSet rs)throws SQLException{
        HashMap<Integer,Integer> hs=new HashMap<>();
        ArrayList<Integer> tripList=new ArrayList<>();
        HashMap<Integer,Integer> hm=new HashMap<>();
        System.out.println("***************************************************************************************************");
        System.out.printf("%-8s %-8s %-18s %-18s %-5s %-15s %-10s %n","bus_id",
                "trip_id","Boarding Point","Destination Point" ,"Cost", "Date", "Boarding Time");
        System.out.println("****************************************************************************************************");
        do {
            int busId = rs.getInt("bus_id");
            int tripId = rs.getInt("tripid");
            hm.put(tripId,busId);
            tripList.add(tripId);
            String bp=rs.getString("boardingPoint");
            String dp=rs.getString("Destination");

            int cost = rs.getInt("cost");
            hs.put(busId,cost);

            String date= String.valueOf(rs.getDate("date"));
            String time=String.valueOf(rs.getTime("travel_time"));
            System.out.printf("%-8s %-8s %-18s %-18s %-5s %-15s %-10s %n",busId,
                    tripId,bp,dp,  cost, date, time);
        } while (rs.next());
        System.out.println("******************************************************");
        do{
            System.out.print("Enter the Trip Id :");
            int preftripId=sc.nextInt();
            System.out.println();
            int busid=hm.get(preftripId);




            if(tripList.contains(preftripId))
            {

                int avail_capacity=Booking.view_bus(preftripId);
                System.out.println("Available seats :"+avail_capacity);
                Customer obj=new Customer();
//                TourBus.book_tickets(avail_capacity,obj.cus_id_getter(),preftripId,busid,SETC.get_date(busid));
                TourBus.book_tickets(avail_capacity, obj.cus_id_getter(), preftripId,busid, SETC.get_date(busid),hs.get(busid));
                break;
            }
            else {
                System.out.println("Enter the Valid Option");
            }
        }while(true);

    }
}
