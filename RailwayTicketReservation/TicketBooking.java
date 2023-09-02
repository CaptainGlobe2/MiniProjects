package RailwayTicketReservation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TicketBooking {
    static int availLower=1;
    static int availMiddle=1;
    static int availUpper=1;
    static int avaliRac=1;
    static int availWaiting=1;

    static Queue<Integer> waitingList = new LinkedList<>();
    static Queue<Integer> racList = new LinkedList<>();//why we are using queue is to maintain the waiting list person is getting the first accessible for in the queue order
    static List<Integer> bookedTicketList= new ArrayList<>();

    static List<Integer> lowerBerth = new ArrayList<>(Arrays.asList(1));
    static List<Integer> middleBerth = new ArrayList<>(Arrays.asList(1));
    static List<Integer> upperBerth = new ArrayList<>(Arrays.asList(1));
    static List<Integer> racPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer> waitingPostion = new ArrayList<>(Arrays.asList(1));

    static Map<Integer,Passengers> passengers = new HashMap<>();

    public void bookTicket(Passengers p,int berthInfo,String allotedBerth){
        p.number= berthInfo;
        p.alloted=allotedBerth;
        passengers.put(p.passengerId,p);
        bookedTicketList.add(p.passengerId);
        System.out.println("Booked Successfully");
    }


    public void addToRAC(Passengers p,int racInfo,String allotedRAC){
        p.number=racInfo;
        p.alloted=allotedRAC;
        passengers.put(p.passengerId, p);
        racList.add(p.passengerId);
        avaliRac--;
        racPosition.remove(0);
        System.out.println("Added to RAC Successfully ");
    }

    public void addToWaitingList(Passengers p,int waitingListInfo,String allotedWl){
        p.number=waitingListInfo;
        p.alloted=allotedWl;
        passengers.put(p.passengerId, p);
        waitingList.add(p.passengerId);
        availWaiting--;
        waitingPostion.remove(0);
        System.out.println("Added to waiting list successfully");
    }

    public void cancelTicket(int passengerId){
        Passengers p = passengers.get(passengerId);
        passengers.remove(Integer.valueOf(passengerId));
        bookedTicketList.remove(Integer.valueOf(passengerId));

        int positionBooked=p.number;
        System.out.println("Cancelled Successfully");


        if(p.alloted.equals("L")) 
        { 
          availLower++;
          lowerBerth.add(positionBooked);
        }
        else if(p.alloted.equals("M"))
        { 
          availMiddle++;
          middleBerth.add(positionBooked);
        }
        else if(p.alloted.equals("U"))
        { 
          availUpper++;
          upperBerth.add(positionBooked);
        }


        if(racList.size()>0){
            Passengers passengerFromRAC = passengers.get(racList.poll());
            int positionRac = passengerFromRAC.number;
            racPosition.add(positionRac);
            racList.remove(Integer.valueOf(passengerFromRAC.passengerId));
            avaliRac++;
        


        if(waitingList.size()>0)
        {
            
            Passengers passengerFromWaitingList = passengers.get(waitingList.poll());
            int positionWL = passengerFromWaitingList.number;
            waitingList.add(positionWL);
            waitingList.remove(Integer.valueOf(passengerFromWaitingList.passengerId));

            passengerFromWaitingList.number = racPosition.get(0);
            passengerFromWaitingList.alloted = "RAC";
            racPosition.remove(0);
            racList.add(passengerFromWaitingList.passengerId);
            
            availWaiting++;
            avaliRac--;
        }
        Main.bookTicket(passengerFromRAC);
    }


}

public void printAvailable()
    {
        System.out.println("Available Lower Berths "  + availLower);
        System.out.println("Available Middle Berths "  + availMiddle);
        System.out.println("Available Upper Berths "  + availUpper);
        System.out.println("Availabel RACs " + avaliRac);
        System.out.println("Available Waiting List " + waitingList);
        System.out.println("--------------------------");
}


public void printPassengers()
    {
        if(passengers.size() == 0)
        {
            System.out.println("No details of passengers");
            return;
        }
        for(Passengers p : passengers.values())
        {
            System.out.println("PASSENGER ID " + p.passengerId );
            System.out.println(" Name " + p.name );
            System.out.println(" Age " + p.age );
            System.out.println(" Status " + p.number + p.alloted);
            System.out.println("--------------------------");
        }
    }



}

