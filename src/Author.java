public class Author {
    private String name;
    private String nationality;
    private int yearOfBirth;

    // Constructor with new parameters
    public Author(String name, String nationality, int yearOfBirth) {
        this.name = name;
        this.nationality = nationality;
        this.yearOfBirth = yearOfBirth;
    }

    // Getters
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
