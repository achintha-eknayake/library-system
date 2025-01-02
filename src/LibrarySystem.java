import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class LibrarySystem {

    public static class LibraryController {
        private Book[] books;
        private int bookCount = 0;

        public LibraryController(int maxBooks) {
            books = new Book[maxBooks];
            loadData();
        }

        public void addBooks() {
            Scanner scanner = new Scanner(System.in);
            if (bookCount < books.length) {
                // Create an empty record with placeholders for 14 details
                String[] bookRecord = new String[17];  // For 17 details (including authors' details and edition)

                // Set the title
                System.out.print("Enter Book Title: ");
                bookRecord[0] = scanner.nextLine();

                // First author's details
                System.out.print("Enter First Author's Family Name (or press Enter to skip): ");
                bookRecord[1] = scanner.nextLine();
                System.out.print("Enter First Author's First Name (or press Enter to skip): ");
                bookRecord[2] = scanner.nextLine();
                System.out.print("Enter First Author's Nationality (or press Enter to skip): ");
                bookRecord[3] = scanner.nextLine();
                System.out.print("Enter First Author's Birth Year (or press Enter to skip): ");
                bookRecord[4] = scanner.nextLine();

                // Second author's details
                System.out.print("Enter Second Author's Family Name (or press Enter to skip): ");
                bookRecord[5] = scanner.nextLine();
                System.out.print("Enter Second Author's First Name (or press Enter to skip): ");
                bookRecord[6] = scanner.nextLine();
                System.out.print("Enter Second Author's Nationality (or press Enter to skip): ");
                bookRecord[7] = scanner.nextLine();
                System.out.print("Enter Second Author's Birth Year (or press Enter to skip): ");
                bookRecord[8] = scanner.nextLine();

                // Third author's details
                System.out.print("Enter Third Author's Family Name (or press Enter to skip): ");
                bookRecord[9] = scanner.nextLine();
                System.out.print("Enter Third Author's First Name (or press Enter to skip): ");
                bookRecord[10] = scanner.nextLine();
                System.out.print("Enter Third Author's Nationality (or press Enter to skip): ");
                bookRecord[11] = scanner.nextLine();
                System.out.print("Enter Third Author's Birth Year (or press Enter to skip): ");
                bookRecord[12] = scanner.nextLine();

                // Set the year published
                System.out.print("Enter Year Published: ");
                bookRecord[13] = scanner.nextLine();

                // Set the ISBN
                System.out.print("Enter ISBN: ");
                bookRecord[14] = scanner.nextLine();

                // Set whether it's an eBook
                System.out.print("Enter if eBook (true/false): ");
                bookRecord[15] = scanner.nextLine();

                // Set the edition
                System.out.print("Enter Edition: ");
                bookRecord[16] = scanner.nextLine();

                // Convert the array into a CSV string and save it
                String csvRecord = String.join(",", bookRecord);

                // Write the record into the books array (as a placeholder for now, it's not directly related to the Book object)
                books[bookCount++] = new Book(bookRecord[0], bookRecord[14], Boolean.parseBoolean(bookRecord[15]),
                        Integer.parseInt(bookRecord[13]), new Author[] {
                        new Author(bookRecord[1] + " " + bookRecord[2], bookRecord[3], Integer.parseInt(bookRecord[4])),
                        new Author(bookRecord[5] + " " + bookRecord[6], bookRecord[7], Integer.parseInt(bookRecord[8])),
                        new Author(bookRecord[9] + " " + bookRecord[10], bookRecord[11], Integer.parseInt(bookRecord[12]))
                }, Integer.parseInt(bookRecord[16]));

                // Save the data to the file after adding
                saveData();
                System.out.println("Book added successfully.");
            } else {
                System.out.println("Library is full, cannot add more books.");
            }
        }


        private void loadData() {
            try (BufferedReader br = new BufferedReader(new FileReader("src/Library_data.csv"))) {
                String line;
                br.readLine();  // Skip the header
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");

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

            System.out.print("Enter Author Name: ");
            String authorName = scanner.nextLine().trim();

            boolean foundBooks = false;

            for (int i = 0; i < bookCount; i++) {
                Book book = books[i];
                Author[] authors = book.getAuthors();

                for (Author author : authors) {
                    if (author != null && author.getName().equalsIgnoreCase(authorName)) {
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
                        System.out.println();

                        foundBooks = true;
                        break;
                    }
                }
            }

            if (!foundBooks) {
                System.out.println("No books found for the author: " + authorName);
            }
        }
    }

    public static class Book {
        private String title;
        private String isbn;
        private boolean isEbook;
        private int yearPublished;
        private Author[] authors;
        private int edition;

        public Book(String title, String isbn, boolean isEbook, int yearPublished, Author[] authors, int edition) {
            this.title = title;
            this.isbn = isbn;
            this.isEbook = isEbook;
            this.yearPublished = yearPublished;
            this.authors = authors;
            this.edition = edition;
        }

        public String getTitle() {
            return title;
        }

        public String getIsbn() {
            return isbn;
        }

        public boolean isEbook() {
            return isEbook;
        }

        public int getYearPublished() {
            return yearPublished;
        }

        public Author[] getAuthors() {
            return authors;
        }

        public int getEdition() {
            return edition;
        }

        public void setEdition(int edition) {
            this.edition = edition;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public void setEbook(boolean ebook) {
            isEbook = ebook;
        }

        public void setYearPublished(int yearPublished) {
            this.yearPublished = yearPublished;
        }

        public void setAuthors(Author[] authors) {
            this.authors = authors;
        }
    }

    public static class Author {
        private String name;
        private String nationality;
        private int yearOfBirth;

        public Author(String name, String nationality, int yearOfBirth) {
            this.name = name;
            this.nationality = nationality;
            this.yearOfBirth = yearOfBirth;
        }

        public String getName() {
            return name;
        }

        public String getNationality() {
            return nationality;
        }

        public int getYearOfBirth() {
            return yearOfBirth;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public void setYearOfBirth(int yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryController libraryController = new LibraryController(100);

        int choice;
        boolean exit = false;
        while (exit ==false) {
            while (true) {
                // Display the menu until valid(integer) choice is given
                System.out.println("*".repeat(36));
                System.out.println("\t  Welcome to the Library");
                System.out.println("*".repeat(36));
                System.out.println("""
                        1 > View all Books
                        2 > View eBooks
                        3 > View non-eBooks
                        4 > View an author's Books
                        5 > Add Book
                        6 > Edit Book
                        7 > Exit""");
                System.out.println("*".repeat(36));
                System.out.print("Your choice: <User Enters choice from above>  ");
                // Input must be an integer
                try {
                    // Takes input as string and converts to an integer
                    choice = Integer.parseInt(scanner.nextLine().trim());
                    if (choice >= 1 && choice <= 7) {
                        break;  // Valid choice, exit the loop
                    } else {
                        System.out.println("Error: Please enter a number between 1 and 7.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error : Integer required");
                }
            }

            switch (choice) {
                case 1:
                    libraryController.viewAllBooks();
                    break;

                case 2:
                    libraryController.viewEbooks();
                    break;

                case 3:
                    libraryController.viewNonEbooks();
                    break;

                case 4:
                    libraryController.addBooks();
                    System.out.println("Book added successfully.");
                    break;

                case 5:
                    System.out.print("Enter the ISBN of the book to edit: ");
                    String editIsbn = scanner.nextLine();

                    System.out.print("Enter the new title: ");
                    String newTitle = scanner.nextLine();

                    System.out.print("Enter the new year published: ");
                    int newYearPublished = scanner.nextInt();

                    System.out.print("Enter the new edition: ");
                    int newEdition = scanner.nextInt();

                    libraryController.editBook(editIsbn, newTitle, newYearPublished, newEdition);
                    System.out.println("Book edited successfully.");
                    break;

                case 6:
                    libraryController.viewBooksByAuthor(scanner);
                    break;

                case 7:
                    exit= true;
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        }

        scanner.close();

    }

}
