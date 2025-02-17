package TaxiDesign;
import java.util.*;

public class Main {
    public static void main(String[] args) {

       Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Taxi Count");
        int taxiCount=sc.nextInt();
        List<Taxi> taxies=Taxi.createTaxi(taxiCount);
       boolean flag=true;
       while(flag){
           System.out.println("1.Book Taxi \n2.Taxi Details \n3.Exit");
           int opt=sc.nextInt();
           switch(opt){
               case 1->Booking.bookTaxi(taxies);
               case 2 ->Taxi.printTaxiData(taxies);


               case 3->flag=false;
           }
       }
    }
}