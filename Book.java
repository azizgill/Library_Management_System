import java.io.Serializable;

public class Book implements Serializable {


private int bookId;
private String title;
private String author;
private String category;
private boolean available;

public Book(int bookId, String title, String author, String category) {
    this.bookId = bookId;
    this.title = title;
    this.author = author;
    this.category = category;
    this.available = true;
}

public int getBookId() {
    return bookId;
}

public String getTitle() {
    return title;
}

public String getAuthor() {
    return author;
}

public String getCategory() {
    return category;
}

public void setTitle(String title) {
    this.title = title;
}

public void setAuthor(String author) {
    this.author = author;
}

public void setCategory(String category) {
    this.category = category;
}

public boolean isAvailable() {
    return available;
}

public void setAvailable(boolean available) {
    this.available = available;
}

public void displayBook() {
    System.out.println("Book ID: " + bookId);
    System.out.println("Title: " + title);
    System.out.println("Author: " + author);
    System.out.println("Category: " + category);
    System.out.println("Available: " + available);
    System.out.println("---------------------------");
}


}
