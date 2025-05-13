package hw6;

import java.util.ArrayList;
import java.util.Scanner;

//  Book Class: Represents a single book in the library
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    // Constructor to initialize a new book with title, author, ISBN
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;  // New books are available by default
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }

    // Setter
    public void setAvailable(boolean available) { isAvailable = available; }

    // Format book details as a readable string
    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn +
               ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

//  Library Class: Manages a collection of books
class Library {
    private ArrayList<Book> books = new ArrayList<>();

    // Add a new book, checking for duplicate ISBN
    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getIsbn().equalsIgnoreCase(book.getIsbn())) {
                System.out.println("A book with this ISBN already exists.");
                return;
            }
        }
        books.add(book);
        System.out.println("Book added successfully.");
    }

    // Remove a book by ISBN
    public void removeBook(String isbn) {
        Book toRemove = null;
        for (Book b : books) {
            if (b.getIsbn().equalsIgnoreCase(isbn)) {
                toRemove = b;
                break;
            }
        }
        if (toRemove != null) {
            books.remove(toRemove);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Display all books currently in the library
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Library is empty.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // Search for books by title (case-insensitive)
    public void searchByTitle(String title) {
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found with the given title.");
        }
    }

    // Search for books by author (case-insensitive)
    public void searchByAuthor(String author) {
        boolean found = false;
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found by the given author.");
        }
    }

    // Mark a book as checked out using its ISBN
    public void checkOutBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equalsIgnoreCase(isbn)) {
                if (b.isAvailable()) {
                    b.setAvailable(false);
                    System.out.println("Book checked out successfully.");
                } else {
                    System.out.println("Book is already checked out.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Mark a book as returned using its ISBN
    public void returnBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equalsIgnoreCase(isbn)) {
                if (!b.isAvailable()) {
                    b.setAvailable(true);
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book was not checked out.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

//  Main Class: Provides a text-based menu for user interaction
public class Main {
    public static void main(String[] args) {
        Library library = new Library();  // Create a Library instance
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Loop until user chooses to exit
        do {
            // Show the menu options
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Search by Title");
            System.out.println("5. Search by Author");
            System.out.println("6. Check Out Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            // Validate input to ensure it's a number
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();  // Discard invalid input
            }

            choice = scanner.nextInt();     // Read menu choice
            scanner.nextLine();             // Consume leftover newline

            String title, author, isbn;     // Common input variables

            // Perform action based on user choice
            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    isbn = scanner.nextLine();
                    library.addBook(new Book(title, author, isbn));
                    break;

                case 2:
                    System.out.print("Enter ISBN to remove: ");
                    isbn = scanner.nextLine();
                    library.removeBook(isbn);
                    break;

                case 3:
                    library.displayAllBooks();
                    break;

                case 4:
                    System.out.print("Enter title to search: ");
                    title = scanner.nextLine();
                    library.searchByTitle(title);
                    break;

                case 5:
                    System.out.print("Enter author to search: ");
                    author = scanner.nextLine();
                    library.searchByAuthor(author);
                    break;

                case 6:
                    System.out.print("Enter ISBN to check out: ");
                    isbn = scanner.nextLine();
                    library.checkOutBook(isbn);
                    break;

                case 7:
                    System.out.print("Enter ISBN to return: ");
                    isbn = scanner.nextLine();
                    library.returnBook(isbn);
                    break;

                case 8:
                    System.out.println("Exiting. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 8);  // Repeat until user exits

        scanner.close();  // Close scanner to free resources
    }
}
