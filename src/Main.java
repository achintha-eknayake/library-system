import java.util.Objects;
import java.util.Scanner;
public class Main {

//    public  static void viewAllBooks()
//    {
//        String[][] dataArray = ReadFile.readCSV();
//        int lines = ReadFile.linesOfFile();
//
//        for(int i=0 ;i< lines;i++)
//        {
//            if(i ==0)continue;
//            System.out.println((i)+".Book:\n "+dataArray[i][0]);
//            System.out.println("Published Year:\n "+dataArray[i][13]);
//            System.out.println("ISBN :\n "+dataArray[i][14]);
//            System.out.println("E-Book:\n "+dataArray[i][15]);
//            System.out.println("Edition:\n "+dataArray[i][16]);
//            System.out.println("Author  :\n "+dataArray[i][1]+" " +dataArray[i][2] );
//            if (!Objects.equals(dataArray[i][5], ""))System.out.println("Co-Author  :\n "+dataArray[i][5]+" " +dataArray[i][5] );
//            if (!Objects.equals(dataArray[i][9], ""))System.out.println("Co-Author  :\n "+dataArray[i][9]+" " +dataArray[i][10] );
//        }
//
//    }
    public static int library_menu(Scanner scanner)
    {
        // Method to show library menu
        //  returns choice

        int choice;

        while(true)
        {
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
            System.out.println("Your choice: <User Enters choice from above>");
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
        return choice ;
    }


    public static void main(String[] args)
    {
        // scanner for input values
        LibraryController library = new LibraryController(100);
        Scanner scanner = new Scanner(System.in);

        int choice = library_menu(scanner);
        // switch statement for each  choice
        switch (choice)
        {
            case 1:
                library.viewAllBooks();

            case 2:
                library.viewEbooks();
                break;
            case 3:
                library.viewNonEbooks();
                break;
            case 4:
                library.viewBooksByAuthor(scanner);
                break;
            case 5:
                System.out.print("Enter the title of the book: ");
                String title = scanner.nextLine();

                System.out.print("Enter the ISBN of the book: ");
                String isbn = scanner.nextLine();

                System.out.print("Is this book an eBook? (yes/no): ");
                boolean isEbook = scanner.nextLine().equalsIgnoreCase("yes");

                System.out.print("Enter the year of publication (e.g., 2020): ");
                int yearPublished = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter the edition number of the book (e.g., 1, 2, etc.): ");
                int edition = Integer.parseInt(scanner.nextLine());

                // Create an array to hold up to 3 authors
                Author[] authors = new Author[3];
                int authorCount = 0;

                // Loop to ask for author details
                while (authorCount < 3) {
                    System.out.println("Enter the details for Author " + (authorCount + 1) + ":");

                    System.out.print("Enter the first name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Enter the last name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Enter the nationality: ");
                    String nationality = scanner.nextLine();

                    System.out.print("Enter the year of birth (e.g., 1975): ");
                    int birthYear = Integer.parseInt(scanner.nextLine());

                    // Add the author to the authors array
                    authors[authorCount++] = new Author(firstName + " " + lastName, nationality, birthYear);

                    // Ask if the user wants to add another author
                    if (authorCount < 3) {
                        System.out.print("Do you want to add another author? (yes/no): ");
                        if (!scanner.nextLine().equalsIgnoreCase("yes")) {
                            break;  // Exit loop if they don't want to add more authors
                        }
                    }
                }

                // Add the new book to the library
                library.addBook(title, isbn, isEbook, yearPublished, authors, edition);
                System.out.println("Book added successfully!");
                break;
            case 6:
                //library.editBook();
                break;
            case 7:
                System.out.println("Library system program closed");
                break;

        }

    }
}