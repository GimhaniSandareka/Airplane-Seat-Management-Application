import java.util.*;
import java.io.File;
public class PlaneManagement {
    private static final int [][] seats = new int[4][]; // Array for seats
    //Initializing seats for each row
    static {
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];
        //Initializing all seats as 0
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = 0;
            }
        }
    }
    private static final Ticket Tickets [][]= new Ticket[4][];  //Array to store tickets
    static {
        Tickets[0] = new Ticket[14];
        Tickets[1] = new Ticket[12];
        Tickets[2] = new Ticket[12];
        Tickets[3] = new Ticket[14];
    }
    private static Scanner input= new Scanner(System.in);
    //To empty the Folder where the ticket files are stored.
    public static void emptyDirectory(){
        File ticketsDir = new File("Tickets Folder");  //Create a file object for the folder
        if (!ticketsDir.exists()) return;  //Check if the folder exists
        try{
            String [] tickets = ticketsDir.list();   //List all files in the folder
            for(String fileName: tickets){
                File subFile = new File(ticketsDir.getPath(), fileName);
                subFile.delete();
            }
        }catch(NullPointerException e){
            System.out.println("Error");
        }
    }
    private static void menu(){
        for (int i = 1; i <= 49; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println("*                  MENU OPTIONS                 *");
        for (int j = 1; j <= 49; j++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println("     1) Buy a seat \n     2) Cancel a seat \n     3) Find first available seat \n     4) Show seating plan \n     5) Print tickets information and total sales \n     6) Search ticket \n     0) Quit");
        for (int k = 1; k <= 49; k++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println("Please select an option: ");
    }
    private static void inputNumber(int number) {
        switch (number) {
            case 1:
                System.out.println("Buy a Seat");
                System.out.println("_________________________");
                buy_seat();
                break;
            case 2:
                System.out.println("Cancel a Seat");
                System.out.println("_________________________");
                cancel_seat();
                break;
            case 3:
                System.out.println("Available first seat");
                System.out.println("_________________________");
                find_first_available();
                break;
            case 4:
                System.out.println("Seating plan");
                show_seating_plan();
                break;
            case 5:
                System.out.println("Print Tickets information and total sales");
                System.out.println("_________________________");
                print_ticket_info();
                break;
            case 6:
                System.out.println("Search Tickets");
                System.out.println("_________________________");
                search_ticket();
                break;
            case 0:
                System.out.println("Quit");
                System.out.println("_________________________");
                System.exit(0);
        }
    }
    private static int inputRowLetter(){
        while (true){
            System.out.println("Enter your row letter (A-D) : ");
            char rowLetter = input.next().toUpperCase().charAt(0);
            int rowIndex;
            switch (rowLetter) {
                case 'A':
                    rowIndex = 0;
                    break;
                case 'B':
                    rowIndex = 1;
                    break;
                case 'C':
                    rowIndex = 2;
                    break;
                case 'D':
                    rowIndex = 3;
                    break;
                default:
                    System.out.println("Invalid row letter!");
                    continue;
            }
            return rowIndex;
        }
    }
    private static int inputSeatNumber(int rowIndex){
        int seatNumber;
        while (true){
            try{
                System.out.println("Enter your seat number\n A,D-14 , B,C-12 :");
                seatNumber= input.nextInt();
                if ((rowIndex==1 || rowIndex==2) && (!(seatNumber>=1 && seatNumber<=12))){
                    System.out.println("Invalid seat number entered!");
                    continue;
                }
                if ((rowIndex==0 || rowIndex==3) && (!(seatNumber>=1 && seatNumber<=14))) {
                    System.out.println("Invalid seat number entered!");
                    continue;
                }
                break;
            }catch (InputMismatchException e){
                System.out.println("Please enter an integer value!");
                input.next();
            }
        }
        return seatNumber;
    }
    public static Person getBuyerInfo(){
        System.out.print("Enter your First name: ");
        String firstName = input.next();
        System.out.print("Enter your Surname: ");
        String surname = input.next();
        String email;
        while (true){
            System.out.print("Enter your Email:  ");
            email = input.next();
            if (!(email.contains("@")&& email.contains("."))){   //Check if email is valid
                System.out.println("Please Enter a valid email. You missed '@' or '.' ! ");
                continue;
            }
            break;
        }
        return new Person(firstName,surname,email);
    }
    public static void reservedTickets(int rowLetter,int seatNumber,int price,Person person){
        Ticket ticket = new Ticket((char)('A'+rowLetter),seatNumber,price,person);  //create a new ticket object
        Tickets[rowLetter][seatNumber-1] = ticket;  //Store ticket in Tickets array
        ticket.save();        //Save ticket information to file
    }
    public static int getPrice(int seatNumber){
        int price=0;
        if (seatNumber>=1 && seatNumber<=5){
            price =200;
        }if (seatNumber>=6 && seatNumber<=9){
            price=150;
        }if(seatNumber>=10 && seatNumber<=14){
            price=180;
        } return price;
    }
    public static void main(String[] args) {
        emptyDirectory();
        System.out.println("\nWelcome to the Plane Management application.\n");
        while (true) {
            try {
                int number;
                do {
                    menu();
                    number = input.nextInt();
                    if (number < 0 || number > 6) {
                        System.out.println("Please enter a number between 0-6 :");
                        continue;
                    }
                    inputNumber(number);
                } while (!(number == 0));
            } catch (InputMismatchException e) {
                input.next();
                System.out.println("Please Enter a integer value!");
                continue;
            } catch (Exception e) {
                System.out.println("Something went wrong!");
                continue;
            }
            break;
        }
    }
    public static void buy_seat() {
        int rowIndex = inputRowLetter();
        int seatNumber = inputSeatNumber(rowIndex);
        while (true) {
            //Check if seat is already sold
            if(seats[rowIndex][seatNumber-1]==1){
                System.out.println("Seat is already sold!\n");
                //Asking user to enter new row letter and seat number
                inputRowLetter();
                inputSeatNumber(rowIndex);
            }
            Person buyer = getBuyerInfo();
            reservedTickets(rowIndex, seatNumber, getPrice(seatNumber), buyer);
            seats[rowIndex][seatNumber - 1] = 1;   //Marking the seat as sold
            System.out.println("Seat sold successfully. \n");
            break;
        }
    }
    public static void cancel_seat() {
        int rowIndex, seatNumber;
        while (true) {
             rowIndex = inputRowLetter();
             seatNumber = inputSeatNumber(rowIndex);
             //Check if seat is not sold
            if (seats[rowIndex][seatNumber - 1] == 0) {
                System.out.println("Seat is not sold yet!");
                continue;
            }
            //Delete the ticket file and mark as available
            Tickets[rowIndex][seatNumber-1].deleteTicketFile();
            Tickets[rowIndex][seatNumber-1] = null;
            seats[rowIndex][seatNumber-1]=0;
            System.out.println("Seat successfully cancelled.\n");
            break;
        }
    }
    private static void find_first_available(){
        int i,j=0;
        boolean is_seat_found = false;
        //Iterate through the seats to find the first available seat
        for (i = 0; i < seats.length; i++) {
            for (j = 0; j < seats[i].length; j++) {
                if (seats[i][j]==0){
                    char rowLetter = (char)('A'+i);
                    int seatNumber= j+1;
                    System.out.println("First available seat: " + rowLetter + seatNumber);
                    is_seat_found = true;
                    break;
                }
            }
            if (is_seat_found){
                break;
            }
        }
        if(!(is_seat_found)){
            System.out.println("Sorry! All the seats are already booked!\n");
        }
    }
    private static void show_seating_plan(){
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                //Display 'O' for available seats and 'X' for sold seats
                System.out.print(seats[i][j]==0? " O":" X");
            }
            System.out.println();
        }
    }
    public static void print_ticket_info(){
        int totalPrice=0;
        System.out.println("Ticket Information: ");
        for (int i = 0; i < Tickets.length; i++) {
            for (int j = 0; j < Tickets[i].length; j++) {
                Ticket ticket = Tickets[i][j];
                if (ticket!=null){
                    System.out.println(ticket.print_ticket_info());
                    totalPrice = totalPrice+ ticket.getPrice();
                }
            }
        }
        System.out.println("Total Price: $" + totalPrice);
    }
    public static void search_ticket(){
        int rowIndex = inputRowLetter();
        int seatNumber = inputSeatNumber(rowIndex);
        //Check if seat is sold
        if (seats[rowIndex][seatNumber-1]==1 && Tickets[rowIndex][seatNumber-1] != null){
            System.out.println("Seat is not available!");
            Ticket ticket = Tickets[rowIndex][seatNumber-1];
            System.out.println(ticket.print_ticket_info());
            System.out.println(ticket.getPerson().printBuyerInfo());
        }
        else{
            System.out.println("Seat is available.");
        }
    }
}
