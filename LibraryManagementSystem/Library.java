import java.util.Scanner;

public class Library{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Books books = new Books();
        Students students = new Students();

        int choice;
        int searchChoice;
        


        do{
            books.dispMenu();
            choice = input.nextInt();

            switch(choice){
                case 1:
                Book book = new Book();
                books.addBook(book);
                break;

                case 2:
                books.upgradeBookQty();
                break;

                case 3:
                System.out.println(" press 1 to Search with Book Serial No.");
                System.out.println(" Press 2 to Search with Book's Author Name.");
                searchChoice = input.nextInt();

                    switch(searchChoice){
                        case 1:
                        books.searchBySno();
                        break;

                        case 2:
                        books.searchByAuthorName();
                    }
                    break;
                case 4:
                books.showAllBooks();
                break;

                case 5:
                Student s = new Student();
                students.addStudents(s);
                break;

                case 6:
                students.showAllStudents();
                break;

                case 7:
                students.checkOutBook(books);
                break;

                case 8:
                students.checkInBook(books);
                break;

                default:
                System.out.println("Enter Betweeb 0 to 8");
            }
        }while(choice!=0);
    }
}