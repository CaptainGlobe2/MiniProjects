import java.util.Scanner;

public class Student {
    String studentName;
    String regNum;//incase the regNo having char


    Book borrowedBooks[]= new Book[3];
    public int booksCount=0;

    Scanner input = new Scanner(System.in);


    public Student(){
        System.out.println("Enter the student Name: ");
        this.studentName= input.nextLine();


        System.out.println("Enter Reg Num: ");
        this.regNum=input.nextLine();
    }
}
