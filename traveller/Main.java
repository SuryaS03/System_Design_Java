package traveller;

import java.sql.SQLException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc=new Scanner(System.in);
        boolean menu=true;
        while(menu){
            System.out.println("1.New User!!");
            System.out.println("2.Login");
            int type=sc.nextInt();
            switch(type){
                case 1:
                {
                    Customer.new_user();


//                    break;
                }
                case 2:
                {
                    Customer.login();
                    menu=false;

                    break;
                }
                default:
                    System.out.println("Invalid Input");
            }
        }
        menu=true;
        while(menu){
        System.out.println("1.Book Tickets");
        System.out.println("2.View Bookings");
        System.out.println("3.Cancel Tickets");
        int opt=sc.nextInt();
        if(opt==3){
            Booking.cancel_tickets();
            break;
        }
        else if(opt==1)
        {
            Ticket.book_ticket();
            break;
        }
        else if(opt==2) {
            Customer obj=new Customer();
            Ticket.view_bookings(obj.cus_id_getter());
            break;
        }
        else {
            System.out.println("Invalid Input");
        }
        }



    }
}
