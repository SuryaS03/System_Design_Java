package traveller;


import java.sql.*;
import java.util.Scanner;

public class TourBus {
    public static Scanner sc=new Scanner(System.in);
    public static void book_tickets(int avail_capacity,int cus_id,int trip_id,int bus_id,String doj,int cost) throws SQLException {
        System.out.println("Enter the Total No.of Tickets :");
        boolean flag=true;
        int tickets=0;
        while(flag){
            tickets=sc.nextInt();
            if(tickets<=avail_capacity){
                Connection con=DBConnection.getConnection();

                String query="Insert into booking (customer_id,bus_id,tickets,doj,tripid,totalcost) values (?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pst.setInt(1,cus_id);
                pst.setInt(2,bus_id);
                pst.setInt(3,tickets);
                pst.setDate(4, Date.valueOf(doj));
                pst.setInt(5,trip_id);
                pst.setInt(6,cost*tickets);

                int result=pst.executeUpdate();

                if(result==1)
                {
                    try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int bookingid = generatedKeys.getInt(1);
                            System.out.println("Total Amount to be paid :"+(cost*tickets));
                            System.out.println("Tickets Has been booked Sucessfully");
                            System.out.println("Generated Booking ID: " + bookingid);

                            System.out.println("Do not forget the Booking ID");
                            flag=false;
                        }
                    }
                }





                break;
            }else
            {
                System.out.println("Enter the Valid Input");
            }
        }

    }
}
