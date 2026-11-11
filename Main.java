import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create library and automatically load saved data
        Library library = new Library();

        int choice;

        do {

            System.out.println("\n========== LIBRARY MANAGEMENT SYSTEM ==========");

            System.out.println("\n--- BOOK MANAGEMENT ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Update Book");
            System.out.println("4. Display All Books");
            System.out.println("5. Search Book by ID");
            System.out.println("6. Search Book by Title");
            System.out.println("7. Search Book by Author");
            System.out.println("8. Search Book by Category");
            System.out.println("9. Display Available Books");

            System.out.println("\n--- MEMBER MANAGEMENT ---");
            System.out.println("10. Register Member");
            System.out.println("11. Display Members");
            System.out.println("12. Search Member");

            System.out.println("\n--- ISSUE AND RETURN ---");
            System.out.println("13. Issue Book");
            System.out.println("14. Return Book");
            System.out.println("15. Display Issued Books");
            System.out.println("16. Transaction History");

            System.out.println("\n0. Exit");

            System.out.println("===============================================");

            System.out.print("Enter your choice: ");
            choice = readInt(sc);

            switch (choice) {

                case 1:
                    System.out.print("Book ID: ");
                    int id = readPositiveInt(sc);

                    System.out.print("Title: ");
                    String title =  readNonEmpty(sc);

                    System.out.print("Author: ");
                    String author = readNonEmpty(sc);

                    System.out.print("Category: ");
                    String category = readNonEmpty(sc);

                    library.addBook(
                            new Book(id, title, author, category));
                    break;

                case 2:
                    System.out.print("Enter Book ID: ");
                    library.removeBook(
                            readPositiveInt(sc));
                    break;

                case 3:
                    System.out.print("Enter Book ID: ");
                    int updateId = readPositiveInt(sc);

                    System.out.print("New Title: ");
                    String newTitle = readNonEmpty(sc);

                    System.out.print("New Author: ");
                    String newAuthor = readNonEmpty(sc);

                    System.out.print("New Category: ");
                    String newCategory = readNonEmpty(sc);

                    library.updateBook(
                            updateId, newTitle, newAuthor, newCategory);
                    break;

                case 4:
                    library.displayBooks();
                    break;

                case 5:
                    System.out.print("Enter Book ID: ");
                    library.searchById(
                            readPositiveInt(sc));
                    break;

                case 6:
                    System.out.print("Enter Title: ");
                    library.searchByTitle(readNonEmpty(sc));
                    break;

                case 7:
                    System.out.print("Enter Author: ");
                    library.searchByAuthor(readNonEmpty(sc));
                    break;

                case 8:
                    System.out.print("Enter Category: ");
                    library.searchByCategory(readNonEmpty(sc));
                    break;

                case 9:
                    library.displayAvailableBooks();
                    break;

                case 10:
                    System.out.print("Member ID: ");
                    int memberId = readPositiveInt(sc);

                    System.out.print("Name: ");
                    String name = readNonEmpty(sc);

                    System.out.print("Email: ");
                    String email = readNonEmpty(sc);

                    System.out.print("Phone: ");
                    String phone = readNonEmpty(sc);

                    library.registerMember(
                            new Member(memberId, name, email, phone));
                    break;

                case 11:
                    library.displayMembers();
                    break;

                case 12:
                    System.out.print("Enter Member ID: ");
                    library.searchMember(
                            readPositiveInt(sc));
                    break;

                case 13:
                    System.out.print("Book ID: ");
                    int issueBookId = readPositiveInt(sc);

                    System.out.print("Member ID: ");
                    int issueMemberId = readPositiveInt(sc);

                    library.issueBook(issueBookId, issueMemberId);
                    break;

                case 14:
                    System.out.print("Book ID: ");
                    library.returnBook(
                            readPositiveInt(sc));
                    break;

                case 15:
                    library.displayIssuedBooks();
                    break;

                case 16:
                    library.displayTransactionHistory();
                    break;

                case 0:
                    System.out.println("Thank you for using LMS!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();

    }
    // safety reads integer from the user

    public static int readInt(Scanner sc) {

        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());

            }

            catch (NumberFormatException e) {
                System.out.print("Invalid input!. Please enter an integer:");
            }
        }

    }
    // safly reads positive integer from the user

    public static int readPositiveInt(Scanner sc){
        
        while (true){
            int num= readInt(sc);

            if(num>0){
                return num;
            }

            else{
                System.out.print("Enter a positive integer: ");
            }
        }
    }
    // Reads text and prevents empty input
public static String readNonEmpty(Scanner sc) {

    while (true) {
        String input = sc.nextLine().trim();

        if (!input.isEmpty()) {
            return input;
        }

        System.out.print("Input cannot be empty. Try again: ");
    }
}
}