import java.io.Serializable;


// Parent class representing a general person
public class Person implements Serializable {

    // Common attributes shared by all persons
    private int id;
    private String name;

    // Constructor to initialize person details
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }
}