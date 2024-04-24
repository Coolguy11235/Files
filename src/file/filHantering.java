package file;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class filHantering {
    String fileName = "personer.txt";

    File myFileObject;

    // Constructor
    public filHantering() {
        createFile();
        write2File();
        readFromFile();
    }

    private void readFromFile() {
        try {
            Scanner myFileScanner = new Scanner(myFileObject);
            while(myFileScanner.hasNextLine()) {
                System.out.println(myFileScanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File could not be found!");
        }

    }

    // Skriver i en fil
    private void write2File() {
        try {
            FileWriter myWriter = new FileWriter(fileName, true);

            Scanner scan = new Scanner(System.in);

            System.out.println("Vad vill du skriva i filen?");
            String input = scan.nextLine();
            myWriter.write(input);

            myWriter.close();

        } catch (IOException e) {
            System.out.println("Kunde inte skriva");
            e.printStackTrace();
        }
    }

    // Skapar en fil
    private void createFile() {
        myFileObject = new File(fileName);
    }
}
