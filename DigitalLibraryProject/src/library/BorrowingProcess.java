
package library;

import java.util.Date;

public class BorrowingProcess {
    private Book book;
    private Borrower borrower;
    private Date borrowDate;
    private Date returnDate;

    public BorrowingProcess(Book book, Borrower borrower, Date borrowDate) {
        this.book = book;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
    }

    public void returnBook(Date returnDate) {
        this.returnDate = returnDate;
        borrower.returnBook(book);
    }

    @Override
    public String toString() {
       return borrower + " borrowed \"" + book.getTitle() + "\" on " + borrowDate +
       (returnDate != null ? " and returned on " + returnDate : "");

    }
}
