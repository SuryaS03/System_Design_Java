package TaxiDesign;
import java.util.*;
public class Taxi {

    public static int idx=0;
    public int taxiNo;
    public char currentPoint;

    public int freeTime;
    public int totalEarning;

    public List<String> trips;



    public Taxi(){
        this.taxiNo=++idx;
        this.currentPoint='A';
        this.freeTime=6;
        this.totalEarning=0;
        this.trips=new ArrayList<>();
    }


    public static List<Taxi> createTaxi(int n){
        List<Taxi> taxies=new ArrayList<>();

        for(int i=1;i<=n;i++){
            Taxi t=new Taxi();
            taxies.add(t);
        }



        return taxies;
    }

        public static void printTaxiData(List<Taxi> taxies){
            System.out.println("TaxiId     CurStop     FreeTime     TotalEarnings");
        for(Taxi t:taxies){
            System.out.println(t.taxiNo+"           "+t.currentPoint+"              "+
                    t.freeTime+"                 "+t.totalEarning );
        }
        }

}
