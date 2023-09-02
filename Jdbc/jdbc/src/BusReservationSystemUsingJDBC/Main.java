package BusReservationSystemUsingJDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*ArrayList<Bus> buses = new ArrayList<Bus>();
        ArrayList<Booking> bookings = new ArrayList<Booking>();

        buses.add(new Bus(1,true,2));
        buses.add(new Bus(2,false,50));
        buses.add(new Bus(3,true,48));*/

        BusDAO busDAO = new BusDAO();

        busDAO.displayBusInfo();

        int userOpt = 1;
        Scanner scanner = new Scanner(System.in);

        /*for(Bus b:buses) {
            b.displayBusInfo();
        }*/

        while(userOpt==1) {
            System.out.println("Enter 1 to Book and 2 to exit");
            userOpt = scanner.nextInt();
            if(userOpt == 1) {
                Booking booking = new Booking();
                if(booking.isAvailable()) {
//
                    BookingDAO bookDao = new BookingDAO();
                    bookDao.addBooking(booking);
                    System.out.println("Your booking is confirmed");
                }
                else
                    System.out.println("Sorry. Bus is full. Try another bus or date.");
            }
        }
    }
}