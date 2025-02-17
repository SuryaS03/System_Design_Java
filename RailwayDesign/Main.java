package RailwayDesign;

import java.util.*;
public class Main {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        while(flag){
            System.out.println("1.Book Tickets \n2.Cancel Ticket \n3.Available Tickets " +
                    "\n4.Booked Ticket \n5.Exit");
            int opt=sc.nextInt();
            switch(opt){
                case 1->Booking.userData();
                case 2->Booking.cancelTicket();
                case 3->Booking.availableTickets();
                case 4->Booking.bookedDetails();
                case 5 ->flag=false;
            }
        }
    }
}