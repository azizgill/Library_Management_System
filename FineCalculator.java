import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Calculates fines for books returned after their due date
public class FineCalculator {

    // Fine charged per overdue day
    private static final double FINE_PER_DAY = 5.0;

    // Calculates the total fine
    public static double calculateFine(LocalDate dueDate, LocalDate returnDate) {

        // Calculate the number of overdue days
        long overdueDays = ChronoUnit.DAYS.between(dueDate, returnDate);

        // No fine if book is returned on time
        if (overdueDays <= 0) {
            return 0;
        }

        // Calculate fine
        return overdueDays * FINE_PER_DAY;
    }

    // Returns the number of overdue days
    public static long calculateOverdueDays(LocalDate dueDate, LocalDate returnDate) {

        long days = ChronoUnit.DAYS.between(dueDate, returnDate);

        return Math.max(0, days);
    }
    
}