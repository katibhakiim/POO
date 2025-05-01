
package library;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowerTest {

    @Test
    public void testBorrowAndReturnBook() {
        Borrower borrower = new Borrower("Ali", "20230001");
        Book book = new PaperBook("Java Basics", "John Doe", "123456");

        assertEquals(0, borrower.getBorrowedBooks().size());

        borrower.borrowBook(book);
        assertTrue(book.isBorrowed());
        assertEquals(1, borrower.getBorrowedBooks().size());

        borrower.returnBook(book);
        assertFalse(book.isBorrowed());
        assertEquals(0, borrower.getBorrowedBooks().size());
    }
}
