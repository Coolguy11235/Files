package file;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class filHantering {
    String textFileName = "text.txt";
    String personFileName = "personer.txt";

    File textFileObject;
    File personFileObject;
    FileWriter myWriter;

    // Constructor
    public filHantering() {
        textFileObject = new File(textFileName);
        personFileObject = new File(personFileName);
        write2File();
        readFromTextFile();
        readFromPersonFile();
    }

    private void readFromTextFile() {
        try {
            Scanner textFileScanner = new Scanner(textFileObject);
            System.out.println("Innehållet i text.txt: ");
            while (textFileScanner.hasNextLine()) {
                System.out.println(textFileScanner.nextLine());
            }
            textFileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("text.txt kunde inte hittas!");
        }

    }

    private void readFromPersonFile() {
        try {
            Scanner personFileScanner = new Scanner(personFileObject);
            System.out.println("Innehållet i personer.txt: ");
            while (personFileScanner.hasNextLine()) {
                System.out.println(personFileScanner.nextLine());
            }
            personFileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("personer.txt kunde inte hittas!");
        }

    }

    // Skriver i en fil
    private void write2File() {
        try {
            myWriter = new FileWriter(textFileName, true);
            Scanner scan = new Scanner(System.in);

            System.out.println("Vad vill du skriva i filen?");
            String input = scan.nextLine();
            myWriter.write(input + "\n");

            myWriter.close();

        } catch (IOException e) {
            System.out.println("Kunde inte skriva till text.txt!");
            e.printStackTrace();
        }
    }
}
