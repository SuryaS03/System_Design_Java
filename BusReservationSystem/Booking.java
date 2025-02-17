package ReservationSystem;
import java.sql.SQLException;
import java.util.*;
import java.lang.*;
import java.text.*;
public class Booking {
    String passenger_name;
     int busNo;
   java.util.Date date;
    Booking(Scanner sc){
        System.out.println("Bus No");
         busNo=sc.nextInt();
        System.out.print("Enter Your Name :");
        System.out.println();
       passenger_name=sc.next();
        System.out.println("Enter Date dd-mm-yyyy");
        String dateInput=sc.next();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        try{
        date=dateFormat.parse(dateInput);}
        catch(Exception e){
            e.printStackTrace();
        }}
public boolean isAvailable() throws SQLException {
        BusDAO busdao=new BusDAO();
        BookingDAO bookdao=new BookingDAO();
int capacity=busdao.getCapacity(busNo);
int booked=bookdao.getBookedCount(busNo,date);

return booked<capacity;

}

        }




