public class Book {
    private String title;
    private String isbn;
    private boolean isEbook;
    private int yearPublished;
    private Author[] authors;
    private int edition; // Added field for edition

    // Updated constructor with edition included
    public Book(String title, String isbn, boolean isEbook, int yearPublished, Author[] authors, int edition) {
        this.title = title;
        this.isbn = isbn;
        this.isEbook = isEbook;
        this.yearPublished = yearPublished;
        this.authors = authors;
        this.edition = edition;
    }

    // Getters
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

    // Setter for edition in case edition needs to be modified
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
