// Member inherits common properties from Person
public class Member extends Person {

    // Member-specific attributes
    private String email;
    private String phone;

    // Constructor
    public Member(int id, String name, String email, String phone) {

        // Calls the parent class constructor
        super(id, name);

        this.email = email;
        this.phone = phone;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for phone
    public String getPhone() {
        return phone;
    }

    // Displays member information
    public void displayMember() {
        System.out.println("Member ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("--------------------------");
    }
}