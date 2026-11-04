import java.util.ArrayList;
import java.time.LocalDate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

// Library class manages books, members and transactions
public class Library {

    // Stores all books
    private ArrayList<Book> books = new ArrayList<>();

    // Stores all registered members
    private ArrayList<Member> members = new ArrayList<>();

    // Stores currently issued books
    private ArrayList<IssueRecord> issuedBooks = new ArrayList<>();

    // Stores complete transaction history
    private ArrayList<IssueRecord> transactionHistory = new ArrayList<>();


    // ================= BOOK MANAGEMENT =================

    // Adds a new book
    public void addBook(Book book) {

        // Check whether Book ID already exists
        for (Book b : books) {

            if (b.getBookId() == book.getBookId()) {
                System.out.println("Book ID already exists!");
                return;
            }
        }

        books.add(book);
        saveData();  // Save data after adding a book

        System.out.println("Book added successfully!");
    }


    // Removes a book using Book ID
    public void removeBook(int id) {

        for (int i = 0; i < books.size(); i++) {

            if (books.get(i).getBookId() == id) {

                // Prevent removing an issued book
                if (!books.get(i).isAvailable()) {
                    System.out.println("Cannot remove an issued book!");
                    return;
                }

                books.remove(i);
                saveData();  // Save data after removing a book

                System.out.println("Book removed successfully!");
                return;
            }
        }

        System.out.println("Book not found!");
    }


    // Updates existing book details
    public void updateBook(int id, String title,
                           String author, String category) {

        for (Book b : books) {

            if (b.getBookId() == id) {

                b.setTitle(title);
                b.setAuthor(author);
                b.setCategory(category);

                 saveData(); // Save updated book details

                System.out.println("Book updated successfully!");
                return;
            }
        }

        System.out.println("Book not found!");
    }


    // Displays all books
    public void displayBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available!");
            return;
        }

        for (Book b : books) {
            b.displayBook();
        }
    }


    // Searches book using ID
    public void searchById(int id) {

        for (Book b : books) {

            if (b.getBookId() == id) {

                System.out.println("Book Found!");
                b.displayBook();
                return;
            }
        }

        System.out.println("Book not found!");
    }


    // Searches books using title
    public void searchByTitle(String title) {

        boolean found = false;

        for (Book b : books) {

            if (b.getTitle().equalsIgnoreCase(title)) {

                b.displayBook();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching book found!");
        }
    }


    // Searches books using author
    public void searchByAuthor(String author) {

        boolean found = false;

        for (Book b : books) {

            if (b.getAuthor().toLowerCase()
                    .contains(author.toLowerCase())) {

                b.displayBook();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found!");
        }
    }


    // Searches books using category
    public void searchByCategory(String category) {

        boolean found = false;

        for (Book b : books) {

            if (b.getCategory().equalsIgnoreCase(category)) {

                b.displayBook();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found!");
        }
    }


    // Displays only available books
    public void displayAvailableBooks() {

        boolean found = false;

        for (Book b : books) {

            if (b.isAvailable()) {

                b.displayBook();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available books!");
        }
    }


    // ================= MEMBER MANAGEMENT =================

    // Registers a new member
    public void registerMember(Member member) {

        // Check duplicate Member ID
        for (Member m : members) {

            if (m.getId() == member.getId()) {
                System.out.println("Member ID already exists!");
                return;
            }
        }

        members.add(member);
        saveData();

        System.out.println("Member registered successfully!");
    }


    // Displays all members
    public void displayMembers() {

        if (members.isEmpty()) {
            System.out.println("No members registered!");
            return;
        }

        for (Member m : members) {
            m.displayMember();
        }
    }


    // Searches member using ID
    public void searchMember(int id) {

        for (Member m : members) {

            if (m.getId() == id) {

                System.out.println("Member Found!");
                m.displayMember();
                return;
            }
        }

        System.out.println("Member not found!");
    }


    // ================= ISSUE MANAGEMENT =================

    // Issues a book to a registered member
    public void issueBook(int bookId, int memberId) {

        Book selectedBook = null;
        Member selectedMember = null;

        // Find requested book
        for (Book b : books) {

            if (b.getBookId() == bookId) {
                selectedBook = b;
                break;
            }
        }

        // Find registered member
        for (Member m : members) {

            if (m.getId() == memberId) {
                selectedMember = m;
                break;
            }
        }

        // Check whether book exists
        if (selectedBook == null) {
            System.out.println("Book not found!");
            return;
        }

        // Check whether member exists
        if (selectedMember == null) {
            System.out.println("Member not registered!");
            return;
        }

        // Check book availability
        if (!selectedBook.isAvailable()) {
            System.out.println("Book is already issued!");
            return;
        }

        // Create issue record
        IssueRecord record =
                new IssueRecord(selectedBook, selectedMember);

        // Store active issue record
        issuedBooks.add(record);

        // Store transaction history
        transactionHistory.add(record);

        // Mark book unavailable
        selectedBook.setAvailable(false);
         saveData(); // Save updated book details

        System.out.println("Book issued successfully!");
        System.out.println("Due Date: " + record.getDueDate());
    }


    // Displays currently issued books
    public void displayIssuedBooks() {

        if (issuedBooks.isEmpty()) {
            System.out.println("No books are currently issued!");
            return;
        }

        for (IssueRecord record : issuedBooks) {
            record.displayRecord();
        }
    }


    // ================= RETURN MANAGEMENT =================

    // Returns a previously issued book
    public void returnBook(int bookId) {

        for (int i = 0; i < issuedBooks.size(); i++) {

            IssueRecord record = issuedBooks.get(i);

            if (record.getBook().getBookId() == bookId) {

                // Get today's date
                LocalDate returnDate = LocalDate.now();

                // Get due date
                LocalDate dueDate = record.getDueDate();

                // Calculate overdue days
                long overdueDays =
                        FineCalculator.calculateOverdueDays(
                                dueDate, returnDate);

                // Calculate fine
                double fine =
                        FineCalculator.calculateFine(
                                dueDate, returnDate);

                // Store return date and fine
                record.completeReturn(returnDate, fine);

                // Make book available again
                record.getBook().setAvailable(true);

                // Remove from active issued list
                // Transaction history remains unchanged
                issuedBooks.remove(i);
                 saveData(); // Save updated book details

                System.out.println("Book returned successfully!");
                System.out.println("Return Date: " + returnDate);
                System.out.println("Overdue Days: " + overdueDays);
                System.out.println("Fine Amount: Rs. " + fine);

                return;
            }
        }

        System.out.println("This book is not currently issued!");
    }


    // ================= TRANSACTION HISTORY =================

    // Displays all issue and return transactions
    public void displayTransactionHistory() {

        if (transactionHistory.isEmpty()) {
            System.out.println("No transaction history found!");
            return;
        }

        for (IssueRecord record : transactionHistory) {

            System.out.println("\nBOOK: " +
                    record.getBook().getTitle());

            System.out.println("Book ID: " +
                    record.getBook().getBookId());

            System.out.println("Member: " +
                    record.getMember().getName());

            System.out.println("Issue Date: " +
                    record.getIssueDate());

            System.out.println("Due Date: " +
                    record.getDueDate());

            // Check whether book has been returned
            if (record.getReturnDate() == null) {

                System.out.println("Status: Currently Issued");

            } else {

                System.out.println("Return Date: " +
                        record.getReturnDate());

                System.out.println("Fine: Rs. " +
                        record.getFine());

                System.out.println("Status: Returned");
            }

            System.out.println("--------------------------------");
        }
    }

    // Constructor loads previously saved data
public Library() {
    loadData();
}

// Saves all library data into a file
private void saveData() {

    try (ObjectOutputStream out =
            new ObjectOutputStream(
                    new FileOutputStream("library.dat"))) {

        out.writeObject(books);
        out.writeObject(members);
        out.writeObject(issuedBooks);
        out.writeObject(transactionHistory);

        System.out.println("Data saved successfully!");

    } catch (IOException e) {
        System.out.println("Error saving data: " + e.getMessage());
    }
}

// Loads previously saved library data
@SuppressWarnings("unchecked")
private void loadData() {

    File file = new File("library.dat");

    // First time running: no saved file exists
    if (!file.exists()) {
        System.out.println("Starting a new library system.");
        return;
    }

    try (ObjectInputStream in =
            new ObjectInputStream(
                    new FileInputStream(file))) {

        books = (ArrayList<Book>) in.readObject();
        members = (ArrayList<Member>) in.readObject();
        issuedBooks = (ArrayList<IssueRecord>) in.readObject();
        transactionHistory = (ArrayList<IssueRecord>) in.readObject();

        System.out.println("Previous library data loaded!");

    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error loading data: " + e.getMessage());
    }
}

}