
package library;

import java.util.*;

public class LibraryApp {
    private static List<Book> books = new ArrayList<>();
    private static List<Borrower> borrowers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Digital Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Borrower");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("6. Search Borrower");
            System.out.println("7. Show Borrowed Books");
            System.out.println("8. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addBook();
                case 2 -> addBorrower();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> searchBook();
                case 6 -> searchBorrower();
                case 7 -> showBorrowedBooks();
                case 8 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Type (1-Paper, 2-EBook): ");
        int type = scanner.nextInt();
        scanner.nextLine();
        Book book = (type == 1) ? new PaperBook(title, author, isbn) : new EBook(title, author, isbn);
        books.add(book);
        System.out.println("Book added.");
    }

    private static void addBorrower() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("University ID: ");
        String id = scanner.nextLine();
        borrowers.add(new Borrower(name, id));
        System.out.println("Borrower added.");
    }

    private static void borrowBook() {
        System.out.print("Enter ISBN of book: ");
        String isbn = scanner.nextLine();
        Book book = books.stream().filter(b -> b.getIsbn().equals(isbn) && !b.isBorrowed()).findFirst().orElse(null);
        if (book == null) {
            System.out.println("Book not available.");
            return;
        }
        System.out.print("Enter borrower ID: ");
        String id = scanner.nextLine();
        Borrower borrower = borrowers.stream().filter(b -> b.getUniversityId().equals(id)).findFirst().orElse(null);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }
        borrower.borrowBook(book);
        System.out.println("Book borrowed.");
    }

    private static void returnBook() {
        System.out.print("Enter ISBN of book to return: ");
        String isbn = scanner.nextLine();
        for (Borrower b : borrowers) {
            for (Book book : b.getBorrowedBooks()) {
                if (book.getIsbn().equals(isbn)) {
                    b.returnBook(book);
                    System.out.println("Book returned.");
                    return;
                }
            }
        }
        System.out.println("Book not found in borrowed list.");
    }

    private static void searchBook() {
        System.out.print("Enter title or author: ");
        String query = scanner.nextLine().toLowerCase();
        books.stream()
            .filter(b -> b.getTitle().toLowerCase().contains(query) || b.getAuthor().toLowerCase().contains(query))
            .forEach(System.out::println);
    }

    private static void searchBorrower() {
        System.out.print("Enter name or ID: ");
        String query = scanner.nextLine().toLowerCase();
        borrowers.stream()
            .filter(b -> b.getName().toLowerCase().contains(query) || b.getUniversityId().toLowerCase().contains(query))
            .forEach(System.out::println);
    }

    private static void showBorrowedBooks() {
        System.out.print("Enter borrower ID: ");
        String id = scanner.nextLine();
        Borrower borrower = borrowers.stream().filter(b -> b.getUniversityId().equals(id)).findFirst().orElse(null);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }
        borrower.getBorrowedBooks().forEach(System.out::println);
    }
}
