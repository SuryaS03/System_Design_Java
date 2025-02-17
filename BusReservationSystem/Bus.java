package ReservationSystem;
import java.util.*;
import java.lang.*;

public class Bus {
    private int busNo;
    private boolean AC;
    private int capacity;
     Bus(int busNo,boolean AC,int capacity){
          this.busNo=busNo;
          this.AC=AC;
          this.capacity=capacity;
     }
     public int getBusNo(){
          return busNo;
     }
     public int getCapacity(){
          return capacity;
     }
     public void setCapacity(int cap){
          capacity=cap;
     }
     public boolean isAC() {
          return AC;
     }
     public void setAc(boolean ac){
          AC=ac;
     }
public void DisBusInfo(){
     System.out.println("Bus NO :"+busNo);
     if(AC==true)
     System.out.println("AC YES");
     else
          System.out.println("AC NO");
     System.out.println("Total Capacity :"+capacity);
}



}
