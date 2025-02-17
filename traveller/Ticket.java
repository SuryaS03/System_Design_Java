package traveller;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class Ticket {
    public static Scanner sc=new Scanner(System.in);
    public static void book_ticket() throws SQLException {
      boolean  menu=true;
        while(menu){
            System.out.println("Menu");
            System.out.println("1.Vacation");
            System.out.println("2.Journey");
            System.out.println("3.Exit");
            System.out.print("Enter Your Choice :");
            System.out.println();
            int pref=sc.nextInt();
            switch(pref){
                case 1:
                {
                    Agency ag=new Agency();
                    ag.agencyInfo();
                    menu=false;
                    break;


                }
                case 2:{
                    SETC.displaySetcInfo();

                    menu=false;
                    break;
                }
                case 3:
                    menu=false;
            }
        }
    }
    public static void view_bookings(int cus_id) throws SQLException {


        Connection con=DBConnection.getConnection();
        String query="select * from booking where customer_id="+cus_id;
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        System.out.printf("%-10s %-8s %-10s %-20s %-20s %n",
                "Booking Id","Bus Id","Tickets","Date of Journey","Total Amount(Rs)");
        System.out.println("********************************************************************");
        while(rs.next()){
            int book_id=rs.getInt("booking_id");
            int bus_id=rs.getInt("bus_id");
            int tickets=rs.getInt("tickets");
            String doj=String.valueOf(rs.getDate("doj"));
            int cost=rs.getInt("totalcost");
            System.out.printf("%-8s %-8s %-10s %-20s %-20s %n",book_id,bus_id,tickets,doj,cost);

        };
        System.out.println("********************************************************************");


    }
}
