import java.io.*;
import java.util.Objects;
import java.util.Scanner;


public class LibraryController {
    private Book[] books;
    private int bookCount = 0;

    String[][] data = ReadFile.readCSV();


    public LibraryController(int maxBooks) {
        books = new Book[maxBooks];
        loadData();


    }
    private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/Library_data.csv"))) {
            String line;
            br.readLine();  // Skip the header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Create a Book object and set its values using setters
                Book book = new Book("", "", false, 0, new Author[3], 0);  // Initialize an empty book object

                book.setTitle(data[0]);  // Title
                book.setYearPublished(Integer.parseInt(data[13]));  // Year Published
                book.setIsbn(data[14]);  // ISBN
                book.setEbook(Boolean.parseBoolean(data[15]));  // eBook
                book.setEdition(Integer.parseInt(data[16]));  // Edition

                // Create and set authors
                Author[] authors = new Author[3];
                int authorIndex = 0;

                // First author
                if (!Objects.equals(data[1], "")) {
                    Author author1 = new Author("", "", 0);  // Initialize an empty author object
                    author1.setName(data[1] + " " + data[2]);  // First Name + Family Name
                    author1.setNationality(data[3]);  // Nationality
                    author1.setYearOfBirth(Integer.parseInt(data[4]));  // Birth Year
                    authors[authorIndex++] = author1;
                }

                // Second author
                if (!Objects.equals(data[5], "")) {
                    Author author2 = new Author("", "", 0);
                    author2.setName(data[5] + " " + data[6]);
                    author2.setNationality(data[7]);
                    author2.setYearOfBirth(Integer.parseInt(data[8]));
                    authors[authorIndex++] = author2;
                }

                // Third author
                if (!Objects.equals(data[9], "")) {
                    Author author3 = new Author("", "", 0);
                    author3.setName(data[9] + " " + data[10]);
                    author3.setNationality(data[11]);
                    author3.setYearOfBirth(Integer.parseInt(data[12]));
                    authors[authorIndex] = author3;
                }

                // Set the authors array in the book
                book.setAuthors(authors);

                // Add the book to the library
                books[bookCount++] = book;
            }
        } catch (IOException e) {
            System.out.println("Error reading data file: " + e.getMessage());
        }
    }
    public void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/Library_data.csv"))) {
            for (int i = 0; i < bookCount; i++) {
                Book book = books[i];
                bw.write(book.getTitle() + "," + book.getIsbn() + "," + book.isEbook() + "," + book.getYearPublished() + "," + book.getEdition()); // Write edition

                // Write author information
                for (Author author : book.getAuthors()) {
                    if (author != null) {
                        bw.write("," + author.getName() + "," + author.getNationality() + "," + author.getYearOfBirth());
                    }
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data file: " + e.getMessage());
        }
    }

    public void viewAllBooks() {
        System.out.println("*".repeat(36));
        System.out.println("\t   All books ");
        System.out.println("*".repeat(36));
        for (int i = 0; i < bookCount; i++) {
            Book book = books[i];
            System.out.println(book.getTitle() + " (ISBN: " + book.getIsbn() + ", Year: " + book.getYearPublished() +
                    ", Edition: " + book.getEdition() + ", eBook: " + book.isEbook() + ")");
        }
    }

    public void viewEbooks() {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isEbook()) {
                System.out.println(books[i].getTitle() + " (Edition: " + books[i].getEdition() + ")");
            }
        }
    }

    public void viewNonEbooks() {
        for (int i = 0; i < bookCount; i++) {
            if (!books[i].isEbook()) {
                System.out.println(books[i].getTitle() + " (Edition: " + books[i].getEdition() + ")");
            }
        }
    }

    public void addBook(String title, String isbn, boolean isEbook, int yearPublished, Author[] authors, int edition) {
        if (bookCount < books.length) {
            books[bookCount++] = new Book(title, isbn, isEbook, yearPublished, authors, edition);
            saveData();
        } else {
            System.out.println("Library is full, cannot add more books.");
        }
    }

    public void editBook(String isbn, String newTitle, int newYearPublished, int newEdition) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                books[i] = new Book(newTitle, isbn, books[i].isEbook(), newYearPublished, books[i].getAuthors(), newEdition);  // Updated to include edition
                saveData();
                return;
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found.");
    }

    public void viewBooksByAuthor(Scanner scanner) {
        System.out.println("************************************");
        System.out.println(" Books by Author");
        System.out.println("************************************");

        // Prompt for the author's name
        System.out.print("Enter Author Name: ");
        String authorName = scanner.nextLine().trim();

        boolean foundBooks = false; // Flag to check if any books were found

        // Iterate through the books to find those by the specified author
        for (int i = 0; i < bookCount; i++) {
            Book book = books[i];
            Author[] authors = book.getAuthors();

            // Check if the book has any authors matching the input name
            for (Author author : authors) {
                if (author != null && author.getName().equalsIgnoreCase(authorName)) {
                    // Print book details in the specified format
                    System.out.println("Book:");
                    System.out.println("Title: " + book.getTitle());
                    System.out.println("Published: " + book.getYearPublished());
                    System.out.println("ISBN: " + book.getIsbn());
                    System.out.println("eBook: " + (book.isEbook() ? "true" : "false"));
                    System.out.println("Edition: " + book.getEdition());
                    System.out.println("Author:");
                    System.out.println("Name: " + author.getName());
                    System.out.println("Nationality: " + author.getNationality());
                    System.out.println("Born: " + author.getYearOfBirth());
                    System.out.println(); // Add an empty line for separation

                    foundBooks = true; // Set flag to true when books are found
                    break; // No need to check further authors for this book
                }
            }
        }

        // If no books were found, inform the user
        if (!foundBooks) {
            System.out.println("No books found for the author: " + authorName);
        }
    }



}
