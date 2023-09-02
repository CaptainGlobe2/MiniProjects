package RailwayTicketReservation;

import java.util.Scanner;

public class Main {

    public static void bookTicket(Passengers p){
        TicketBooking booking = new TicketBooking();

        if(TicketBooking.availWaiting==0){
            System.out.println("No Tickets Available");
            return;
        }

        if((p.brethPreference.equals("L")&&TicketBooking.availLower>0)||(p.brethPreference.equals("M")&&TicketBooking.availMiddle>0)||(p.brethPreference.equals("U")&&TicketBooking.availUpper>0)){
            System.out.println("Preferred Berth is Available ");

            if(p.brethPreference.equals("L")){
                System.out.println("Lower Berth Given ");
                booking.bookTicket(p,(TicketBooking.lowerBerth.get(0)),"L");
                TicketBooking.lowerBerth.remove(0);
                TicketBooking.availLower--;
            }
            else if(p.brethPreference.equals("M")){
                System.out.println("Middle Berth Given");
                booking.bookTicket(p, (TicketBooking.middleBerth.get(0)), "M");
                TicketBooking.middleBerth.remove(0);
                TicketBooking.availMiddle--;

            }

            else if(p.brethPreference.equals("U")){
                System.out.println("Upper Berth Given");
                booking.bookTicket(p, (TicketBooking.upperBerth.get(0)), "M");
                TicketBooking.upperBerth.remove(0);
                TicketBooking.availUpper--;
                
            }
        }

        //if the prefered berth is not available then book the available berth
        else if(TicketBooking.availLower>0){
            System.out.println("Lower Berth Given ");
            booking.bookTicket(p, (TicketBooking.lowerBerth.get(0)), "L");
            TicketBooking.lowerBerth.remove(0);
            TicketBooking.availLower--;
        }

        else if(TicketBooking.availMiddle>0){
            System.out.println("Middle Berth Given ");
            booking.bookTicket(p, (TicketBooking.middleBerth.get(0)), "L");
            TicketBooking.middleBerth.remove(0);
            TicketBooking.availMiddle--;
        }

        else if(TicketBooking.availUpper>0){
            System.out.println("Upper Berth Given ");
            booking.bookTicket(p, (TicketBooking.upperBerth.get(0)), "L");
            TicketBooking.upperBerth.remove(0);
            TicketBooking.availUpper--;
        }

        //if all berths are filled then go to rac
        else if(TicketBooking.avaliRac>0){
            System.out.println("RAC Available");
            booking.addToRAC(p,(TicketBooking.racPosition.get(0)), "RAC");
        }

        else if(TicketBooking.availWaiting > 0)
        {
            System.out.println("Added to Waiting List");
            booking.addToWaitingList(p,(TicketBooking.waitingPostion.get(0)),"WL");
            
        }

    }
    public static void cancelTicket(int id){
        TicketBooking booking = new TicketBooking();
        if(!booking.passengers.containsKey(id)){
            System.out.println("Passenger detail unkown");
        }
        else{
            booking.cancelTicket(id);
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("1.Book Tickets \n2.Cancel Ticket \n3.Available Tickets \n4.Booked Tickets \n5.Exit ");
            int choice = input.nextInt();

            switch(choice){
                case 1:
                System.out.println("Enter Passenger name,age and berth preference (L,M or U)");
                String name = input.next();
                int age = input.nextInt();
                String berthPreference = input.next();
                Passengers p = new Passengers(name, age, berthPreference);
                bookTicket(p);
                break;

                case 2:
                
                 System.out.println("Enter the passenger Id to Cancel");
                    int id=input.nextInt();
                    cancelTicket(id);
                    break;
                
                case 3:
                TicketBooking booking= new TicketBooking();
                booking.printPassengers();
                break;
                

                case 4:
                TicketBooking booking1 = new TicketBooking();
                booking1.printPassengers();
                break;
                case 5:
                System.exit(0);
                break;

                default:
                System.out.println("enter the correct value");
                break;
            }
        }
    }
    private static void Exit(int i) {
    }
}
