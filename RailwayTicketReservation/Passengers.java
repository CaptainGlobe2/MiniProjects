package RailwayTicketReservation;

public class Passengers {
    static int id=1;
    String name;
    int age;
    String brethPreference;
    int passengerId;
    String alloted;
    int number;

    public Passengers(String name,int age,String breathPreference){
        this.name=name;
        this.age=age;
        this.brethPreference=breathPreference;
        this.passengerId=id++;
        alloted="";
        number=-1;
    }
}
