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
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    System.out.print("Book ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Author: ");
                    String author = sc.nextLine();

                    System.out.print("Category: ");
                    String category = sc.nextLine();

                    library.addBook(
                        new Book(id, title, author, category)
                    );
                    break;

                case 2:
                    System.out.print("Enter Book ID: ");
                    library.removeBook(
                        Integer.parseInt(sc.nextLine())
                    );
                    break;

                case 3:
                    System.out.print("Enter Book ID: ");
                    int updateId = Integer.parseInt(sc.nextLine());

                    System.out.print("New Title: ");
                    String newTitle = sc.nextLine();

                    System.out.print("New Author: ");
                    String newAuthor = sc.nextLine();

                    System.out.print("New Category: ");
                    String newCategory = sc.nextLine();

                    library.updateBook(
                        updateId, newTitle, newAuthor, newCategory
                    );
                    break;

                case 4:
                    library.displayBooks();
                    break;

                case 5:
                    System.out.print("Enter Book ID: ");
                    library.searchById(
                        Integer.parseInt(sc.nextLine())
                    );
                    break;

                case 6:
                    System.out.print("Enter Title: ");
                    library.searchByTitle(sc.nextLine());
                    break;

                case 7:
                    System.out.print("Enter Author: ");
                    library.searchByAuthor(sc.nextLine());
                    break;

                case 8:
                    System.out.print("Enter Category: ");
                    library.searchByCategory(sc.nextLine());
                    break;

                case 9:
                    library.displayAvailableBooks();
                    break;

                case 10:
                    System.out.print("Member ID: ");
                    int memberId = Integer.parseInt(sc.nextLine());

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Phone: ");
                    String phone = sc.nextLine();

                    library.registerMember(
                        new Member(memberId, name, email, phone)
                    );
                    break;

                case 11:
                    library.displayMembers();
                    break;

                case 12:
                    System.out.print("Enter Member ID: ");
                    library.searchMember(
                        Integer.parseInt(sc.nextLine())
                    );
                    break;

                case 13:
                    System.out.print("Book ID: ");
                    int issueBookId = Integer.parseInt(sc.nextLine());

                    System.out.print("Member ID: ");
                    int issueMemberId = Integer.parseInt(sc.nextLine());

                    library.issueBook(issueBookId, issueMemberId);
                    break;

                case 14:
                    System.out.print("Book ID: ");
                    library.returnBook(
                        Integer.parseInt(sc.nextLine())
                    );
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
}