package TaxiDesign;
import java.util.*;
public class Booking {
static Scanner sc=new Scanner(System.in);
    public static void bookTaxi(List<Taxi> taxies){
        System.out.println("Enter pickUp Point");
        char picup=sc.next().toUpperCase().charAt(0);
        System.out.println("Enter Drop Point");
        char drop=sc.next().toUpperCase().charAt(0);
        System.out.println("Enter PickUp Time");
        int picTime=sc.nextInt();

        List<Taxi> freeTaxies=getFreeTaxi(picup,picTime,taxies);
        if(freeTaxies.size()==0){
            System.out.println("Taxi Not Available");
            return;
        }
        Collections.sort(freeTaxies,(a,b)->a.totalEarning-b.totalEarning);
        Taxi alotTaxi=null;
        int minDist=999;

        for(Taxi t:freeTaxies){
            int dist=Math.abs(t.currentPoint-picup)*15;
            if(dist<minDist){
                minDist=dist;
                alotTaxi=t;
            }
        }
        String trip=alotTaxi.taxiNo+"         "+picup+"       "+drop+"        "+alotTaxi.totalEarning;
        alotTaxi.trips.add(trip);

        int dist=(Math.abs(alotTaxi.currentPoint-drop)*15)-5;

        alotTaxi.totalEarning= alotTaxi.totalEarning+100+(dist*10);

        alotTaxi.freeTime= alotTaxi.freeTime+(Math.abs(alotTaxi.currentPoint-drop));

        alotTaxi.currentPoint=drop;

        System.out.println("Taxi Booked SuccessFully");
        
        System.out.println("Your Taxi :"+alotTaxi.taxiNo);



    }
    public static List<Taxi> getFreeTaxi(char pickUp,int picTime,List<Taxi> taxies){
        List<Taxi> freeTaxi=new ArrayList<>();

        for(Taxi t:taxies){
            if(t.freeTime<=picTime && (Math.abs((t.currentPoint-'A')-(pickUp-'A'))<=(picTime-t.freeTime)))
                freeTaxi.add(t);
        }

        return freeTaxi;
    }



}
