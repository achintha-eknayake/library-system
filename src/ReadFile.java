import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        String filePath = "src/Library_data.csv"; // Change this to your CSV file path

        // Count total lines in the CSV file
        System.out.println("Total lines in the CSV file: " + linesOfFile());

        // Read the CSV file into a 2D array
        String[][] dataArray = readCSV();

        // Example: Get a value from the 2D array
        int row = 1; // Change this to the desired row index
        int col = 2; // Change this to the desired column index
        String value = getValueAt(dataArray, row, col);
        System.out.println("Value at row " + row + ", column " + col + ": " + value);
    }

    public static int linesOfFile() {
        int lineCount = 0;
        String filePath = "src/Library_data.csv"; // Change this to your CSV file path
        String line; // Declare the line variable

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read the header line
            String headerLine = br.readLine();
            if (headerLine != null) {
                lineCount++; // Increment for header line
            }

            // Read each subsequent line
            while ((line = br.readLine()) != null) {
                lineCount++; // Increment for each data row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineCount; // Return the correct line count
    }

    public static String[][] readCSV() {
        String[][] data = null;
        String line;
        int rowCount = 0;
        String filePath = "src/Library_data.csv"; // Change this to your CSV file path


        // First, count the number of lines to define the array size
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Count rows
            rowCount = linesOfFile(); // You can also count rows in this method
            data = new String[rowCount][];

            // Read the header line
            String headerLine = br.readLine();
            if (headerLine != null) {
                data[0] = headerLine.split(","); // Store the header
            }

            // Read each subsequent line
            int rowIndex = 1; // Start from the second row (after the header)
            while ((line = br.readLine()) != null) {
                data[rowIndex++] = line.split(","); // Split each line and store in the array
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data; // Return the 2D array containing CSV data
    }

    public static String getValueAt(String[][] data, int row, int col) {
        if (data != null && row < data.length && col < data[row].length) {
            return data[row][col]; // Return the value at the specified row and column
        } else {
            return "Invalid row or column index"; // Handle out-of-bounds access
        }
    }
}
