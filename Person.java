public class Person {
    //Attributes
    private String name;
    private String surname;
    private String email;
 //constructor
    public Person(String name,String surname,String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    //Getters and setters for attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String printBuyerInfo(){
        return  "First name: " + getName() + "\n" +
                "Surname: " + getSurname() + "\n" +
                "Email: " + getEmail();
    }
}
