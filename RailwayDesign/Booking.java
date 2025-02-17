package RailwayDesign;

import java.util.*;

public class Booking {

    static Scanner sc=new Scanner(System.in);
    static int availLowerBirth=1;
    static int availMiddleBirth=1;
    static int availUpperBirth=1;
    static int availRac=1;
    static int availWaitingList=1;

    static Queue <Passenger> racList=new LinkedList<>();
    static Queue<Passenger> waitList=new LinkedList<>();
    static List<Integer> bookList=new ArrayList<>();

    static List<Integer> lowerBirthPosition=new ArrayList<>(Arrays.asList(1,2));
    static List<Integer> upperBirthPosition=new ArrayList<>(Arrays.asList(1));
    static List<Integer> middleBirthPosition=new ArrayList<>(Arrays.asList(1));
    static List<Integer> racPosition=new ArrayList<>(Arrays.asList(1));
    static List<Integer> waitPosition=new ArrayList<>(Arrays.asList(1));
   static Map <Integer,Passenger> BookedPassenger=new HashMap<>();

    public static void availableTickets(){
        System.out.println("********************************");
        System.out.println("Lower Birth :"+availLowerBirth);
        System.out.println("Upper Birth :"+availUpperBirth);
        System.out.println("Middle Birth :"+availMiddleBirth);
        System.out.println("RAC :"+availRac);
        System.out.println("Waiting List :"+availWaitingList);
        System.out.println("*********************************");
    }


    public static void bookedDetails(){

        for(Passenger p:BookedPassenger.values()){
            System.out.println("Passenger Id :"+p.pId);
            System.out.println("Passenger Name :"+p.pName);
            System.out.println("Passenger Age :"+p.age);
            System.out.println("Alloted Seat "+p.seatNo+p.birthPref);
            System.out.println("************************************");

        }
    }
public static void userData(){
    System.out.println("Enter Your Name :");
    String name=sc.next();
    System.out.println("Enter Age");
    int age=sc.nextInt();
    System.out.println("Enter Birth Preference U/M/L");
    String bpref=sc.next().toUpperCase();
    Passenger p=new Passenger(name,age,bpref);
    bookTicket(p);



}


public static void bookTicket(Passenger p){
        if(availWaitingList==0)
        {
            System.out.println("Ticket Not Available");
            return;
        }
        if((availLowerBirth>0 && p.birthPref.equals("L")) ||
                (availMiddleBirth>0 && p.birthPref.equals("M"))
                || availUpperBirth>0 && p.birthPref.equals("U"))
        {
            System.out.println("Preffered Birth Given");
            if(p.birthPref.equals("L")){

                p.birthPref="L";
                p.seatNo=lowerBirthPosition.remove(0);
                availLowerBirth--;
                BookedPassenger.put(p.pId,p);
                bookList.add(p.pId);
                System.out.println("------------------Ticket Booked Successfully");




            }else if(p.birthPref.equals("M")){
                p.birthPref="M";
                p.seatNo=middleBirthPosition.remove(0);
                availMiddleBirth--;
                BookedPassenger.put(p.pId,p);
                bookList.add(p.pId);
                System.out.println("------------------Ticket Booked Successfully");




            }else if(p.birthPref.equals("U")){
                p.birthPref="U";
                p.seatNo=upperBirthPosition.remove(0);
                availUpperBirth--;
                BookedPassenger.put(p.pId,p);
                bookList.add(p.pId);
                System.out.println("------------------Ticket Booked Successfully");

            }

    }else if(availLowerBirth>0){
            System.out.println("Lower Birth Given");
            p.birthPref="L";
            p.seatNo=lowerBirthPosition.remove(0);
            availLowerBirth--;
            BookedPassenger.put(p.pId,p);
            bookList.add(p.pId);
            System.out.println("------------------Ticket Booked Successfully");

        }
        else if(availMiddleBirth>0){
            System.out.println("Middle Birth Given");
            p.birthPref="M";
            p.seatNo=middleBirthPosition.remove(0);
            availMiddleBirth--;
            BookedPassenger.put(p.pId,p);
            bookList.add(p.pId);
            System.out.println("------------------Ticket Booked Successfully");

        }
        else if(availUpperBirth>0){
            System.out.println("Upper Birth Given");
            p.birthPref="U";
            p.seatNo=upperBirthPosition.remove(0);
            availUpperBirth--;
            BookedPassenger.put(p.pId,p);
            bookList.add(p.pId);
            System.out.println("------------------Ticket Booked Successfully");

        }
        else if(availRac>0){
            System.out.println("RAC Avvailable");
            p.birthPref="RAC";
            p.seatNo=racPosition.remove(0);
            availRac--;
            BookedPassenger.put(p.pId,p);
            racList.add(p);
            System.out.println("------------------Added to RAC Successfully");

        }else {
            System.out.println("Added to WaitingList");
            p.birthPref="WL";
            p.seatNo=waitPosition.remove(0);
            availWaitingList--;
            BookedPassenger.put(p.pId,p);
            waitList.add(p);
            System.out.println("------------------Added to Waiting List Successfully");
        }



}


        public static void cancelTicket(){
            System.out.println("Enter the Passenger ID");
            int id=sc.nextInt();
            if(!BookedPassenger.containsKey(id))
                System.out.println("Not Exist");
            else{
                Passenger p=BookedPassenger.get(id);
                String birth=p.birthPref;

                BookedPassenger.remove(id);

                bookList.remove(Integer.valueOf(id));
                int sno=p.seatNo;
                if(birth.equals("M")){
                    availMiddleBirth++;
                    middleBirthPosition.add(sno);

                }
                else if(birth.equals("U")){
                    availUpperBirth++;
                   upperBirthPosition.add(sno);
                }
                else{
                    availLowerBirth++;
                    lowerBirthPosition.add(sno);
                }
                System.out.println("-----------Ticket Cancelled SUccesfully");
                if(racList.size()>0){
                    Passenger racPass=racList.poll();

                    availRac++;
                    int racpos=racPass.seatNo;
                    racPosition.add(racpos);
                    if(waitList.size()>0)
                        availWaitingList++;
                    bookTicket(racPass);



                if(waitList.size()>0){
                    Passenger wl=waitList.poll();

                    int wlpos=wl.seatNo;
                    waitPosition.add(wlpos);
                    bookTicket(wl);


                }}

            }

        }


}
