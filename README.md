# Library System

This is a simple library management system implemented in Java. It allows users to manage books and authors, view all books, view eBooks, view non-eBooks, and view books by a specific author.


## Classes

### Author

The `Author` class represents an author with the following attributes:
- `name`: The name of the author.
- `nationality`: The nationality of the author.
- `yearOfBirth`: The year of birth of the author.

### Book

The `Book` class represents a book with the following attributes:
- `title`: The title of the book.
- `isbn`: The ISBN of the book.
- `isEbook`: A boolean indicating whether the book is an eBook.
- `yearPublished`: The year the book was published.
- `authors`: An array of `Author` objects representing the authors of the book.
- `edition`: The edition of the book.

### LibraryController

The `LibraryController` class manages the library's collection of books. It provides methods to:
- Load data from a CSV file.
- Save data to a CSV file.
- View all books.
- View eBooks.
- View non-eBooks.
- Add a new book.
- Edit an existing book.
- View books by a specific author.

### LibrarySystem

The `LibrarySystem` class contains the main method and provides a menu-driven interface for interacting with the library system.

### ReadFile

The `ReadFile` class provides utility methods to read data from a CSV file.

## Usage

1. Clone the repository.
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse, VS Code).
3. Run the `Main` class to start the library system.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Author

Achintha Ekanyake