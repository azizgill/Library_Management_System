import java.time.LocalDate;
import java.io.Serializable;

// Stores details of a book issued to a member
public class IssueRecord implements Serializable {

    private Book book;
    private Member member;

    // Stores issue, due and return dates
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    // Stores fine amount
    private double fine;

    // Constructor
    public IssueRecord(Book book, Member member) {

        this.book = book;
        this.member = member;

        // Automatically gets today's date
        this.issueDate = LocalDate.now();

        // Due date is 14 days after issue date
        this.dueDate = issueDate.plusDays(14);

        // Initially book is not returned
        this.returnDate = null;
        this.fine = 0;
    }

    // Returns book object
    public Book getBook() {
        return book;
    }

    // Returns member object
    public Member getMember() {
        return member;
    }

    // Returns issue date
    public LocalDate getIssueDate() {
        return issueDate;
    }

    // Returns due date
    public LocalDate getDueDate() {
        return dueDate;
    }

    // Returns actual return date
    public LocalDate getReturnDate() {
        return returnDate;
    }

    // Returns fine amount
    public double getFine() {
        return fine;
    }

    // Updates return date and fine
    public void completeReturn(LocalDate returnDate, double fine) {

        this.returnDate = returnDate;
        this.fine = fine;
    }

    // Displays issue information
    public void displayRecord() {

        System.out.println("Book: " + book.getTitle());
        System.out.println("Book ID: " + book.getBookId());

        System.out.println("Member: " + member.getName());
        System.out.println("Member ID: " + member.getId());

        System.out.println("Issue Date: " + issueDate);
        System.out.println("Due Date: " + dueDate);

        System.out.println("--------------------------------");
    }
}