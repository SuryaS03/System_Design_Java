package RailwayDesign;

public class Passenger {
    public String pName;
    public int age;
    public String birthPref;
    static int idx=1;
    public int pId;
    public String alloted;
    public int seatNo;

    public Passenger(String pName,int age,String birthPref){
        this.pName=pName;
        this.age=age;
        this.birthPref=birthPref;
        this.pId=idx++;
        this.alloted="";
        this.seatNo=-1;

    }

}
