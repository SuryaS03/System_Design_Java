package traveller;
import java.sql.*;
import java.util.*;


public class Package {
    static Scanner sc=new Scanner(System.in);
    public static String get_date(int bus_id) throws SQLException {
        Connection con=DBConnection.getConnection();
        String query="Select doj from package where bus_id="+bus_id;
        Statement st= con.createStatement();
        ResultSet rs=st.executeQuery(query);
        if(rs.next()){
            return rs.getString(1);
        }
        return "";

    }
    public static void disPackageInfo(int agency_id) throws SQLException {
        HashMap<Integer,Integer> hs=new HashMap<>();
        Connection con= DBConnection.getConnection();
        String query="select * from package where agency_id="+agency_id;
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);

        System.out.printf("%-8s %-10s %-18s %-5s %-10s %-8s %-10s %n",
                "bus_id", "country", "state", "days", "locations", "cost","Date of Journey");
        System.out.println("-------------------------------------------------------------------------------");
        ArrayList<Integer> busList=new ArrayList<>();
        while (rs.next()) {
            int busId = rs.getInt("bus_id");
            busList.add(busId);
            String country = rs.getString("country");
            String state = rs.getString("state");
            int days = rs.getInt("days");
            int locations = rs.getInt("locations");
            int cost = rs.getInt("cost");
            hs.put(busId,cost);
            String doj= String.valueOf(rs.getDate("doj"));


            System.out.printf("%-8d %-10s %-18s %-5d %-10d %-8d %-10s %n",
                    busId, country, state, days, locations, cost,doj);
        }
        System.out.println("--------------------------------------------------------------------------------");
        do{
            System.out.print("Enter the Bus Id :");
            int prefBusId=sc.nextInt();
            System.out.println();
            int tripid=0;
            if(busList.contains(prefBusId))
            {
               int avail_capacity= Booking.view_seats(prefBusId,tripid);
                System.out.println("Available capacity :"+avail_capacity);
                Customer cusobj=new Customer();
                TourBus.book_tickets(avail_capacity,cusobj.cus_id_getter(),tripid,prefBusId,Package.get_date(prefBusId),hs.get(prefBusId));
                break;
            }
            else {
                System.out.println("Enter the Valid Option");
            }
        }while(true);


    }
}
