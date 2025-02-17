import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Ticket {
    //Attributes
    private char rowLetter;
    private int seatNumber;
    private int price;
    private Person person;
    //Constructor
    public Ticket(int rowLetter,int seatNumber, int price, Person person){
        this.rowLetter =((char)('A' + rowLetter));
        this.seatNumber = seatNumber;
        this.price = price;
        this.person = person;
    }
    //Getters and Setters for attributes
    public char getRowLetter() {
        return rowLetter;
    }

    public void setRowLetter(char rowLetter) {
        this.rowLetter = rowLetter;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

  public String print_ticket_info(){
        System.out.println("Tickets Information: ");
        return "Row Letter: " + getRowLetter() + "\n" +
                "Seat Number: " + getSeatNumber() + "\n" +
                "Price: $" + getPrice() + "\n" +
                "Buyer's Information \n" +
                 getPerson().printBuyerInfo();
    }
    //Method to save ticket information to a file
  public void save(){
       //Constructing the filename based on the row letter and seat number
      String fileName = rowLetter + String.valueOf(seatNumber) + ".txt";
      //Creating a File object where ticket files will save to.
      File directory = new File("Tickets Folder",fileName);
      //Getting the parent folder of the file and assigning it to the File object named file.
      File file = directory.getParentFile();
      if(!(file.exists())){    //Check if file doesn't exist
          file.mkdir();        //If the folder doesn't exist, then make one.
      }
      try(FileWriter writer = new FileWriter(directory)){   //Creating a FileWriter object to write data.
          writer.write(print_ticket_info());    //Writing ticket information
      }catch (IOException e){
          System.out.println("Data writing failed!");
      }
  }

    public void deleteTicketFile(){
        String fileName = getRowLetter() + getSeatNumber() + ".txt";
        File file = new File("Tickets Folder",fileName);
        if (file.exists()){
            file.delete();      //if file exists, then delete it.
        }else{
            System.out.println("File not found");
        }
    }

}



