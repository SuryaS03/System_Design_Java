package ReservationSystem;

import java.sql.SQLException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws SQLException {
BusDAO busdao=new BusDAO();
busdao.displayBusInfo();
        Scanner sc=new Scanner(System.in);
        boolean choice=true;
        while(choice) {
            System.out.println("Enter Your Choice");
            System.out.println("1.Booking");
            System.out.println("2.Exit");
            int opt = sc.nextInt();
            if(opt==1){

                Booking book=new Booking(sc);

                if(book.isAvailable()){

                    BookingDAO bookingdao=new BookingDAO();
                    bookingdao.addBooking(book);
                    System.out.println("Your Booking is Confirmed");

                }else
                {
                    System.out.println("Try again with different date and bus ");
                }
            }

            if(opt==2)
                choice=false;


        }


        
        
    }
}
