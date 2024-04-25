package file;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class filHantering {
    Scanner scan = new Scanner(System.in);
    String textFileName = "text.txt";
    String personFileName = "personer.txt";
    String wordsFileName = "words.txt";
    String svenskaOrdFileName = "svenska-ord.txt";

    File textFileObject;
    File personFileObject;
    File wordsFileObject;
    File svenskaOrdFileObject;
    FileWriter myWriter;

    // Constructor
    public filHantering() {
        System.out.println("Vilken fil vill du använda? (text, personer, words, ord)");
        String val = scan.nextLine();

        if (val.equalsIgnoreCase("text")) {
            textFileObject = new File(textFileName);
            write2File(textFileName);
            readFromFile(textFileObject);
        } else if (val.equalsIgnoreCase("personer")) {
            personFileObject = new File(personFileName);
            readFromFile(personFileObject);
        } else if (val.equalsIgnoreCase("words")) {
            findLongestWord(wordsFileName);
            findMostCommonLetter(wordsFileName);
        } else if (val.equalsIgnoreCase("ord")) {
            findLongestWord(svenskaOrdFileName);
            findMostCommonLetter(svenskaOrdFileName);
        } else {
            System.out.println("Ogiltig svar");
        }
    }

    private void readFromFile(File file) {
        try {
            Scanner fileScanner = new Scanner(file);
            System.out.println("Innehållet i " + file.getName() + ": ");
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println(file.getName() + "kunde inte hittas!");
        }

    }

    // Skriver i en fil
    private void write2File(String fileName) {
        try {
            myWriter = new FileWriter(fileName, true);

            System.out.println("Vad vill du skriva i filen?");
            String input = scan.nextLine();
            myWriter.write(input + "\n");

            myWriter.close();

        } catch (IOException e) {
            System.out.println("Kunde inte skriva till " + fileName + "!");
            e.printStackTrace();
        }
    }

    private void findLongestWord (String fileName) {
        try {
            File fileObject = new File(fileName);
            Scanner wordScanner = new Scanner(fileObject);

            String longestWord = "";
            while (wordScanner.hasNext()) {
                String word = wordScanner.next();
                if (word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }

            if (!longestWord.isEmpty()) {
                System.out.println("Det längsta ordet i " + fileName + " är: " + longestWord);
            } else {
                System.out.println(fileName + " innehåller inga ord");
            }

            wordScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println(fileName + " kunde inte hittas!");
        }
    }

    private void findMostCommonLetter (String fileName) {
        try {
            File fileObject = new File(fileName);
            Scanner scan = new Scanner(fileObject);

            Map<Character, Integer> lettercount = new HashMap<>();

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (char c : line.toCharArray()) {
                    if (Character.isLetter(c)) {
                        char lowercaseC = Character.toLowerCase(c);
                        lettercount.put(lowercaseC, lettercount.getOrDefault(lowercaseC, 0) + 1);
                    }
                }
            }

            char mostCommonLetter = ' ';
            int maxCount = 0;
            for (Map.Entry<Character, Integer> entry : lettercount.entrySet()) {
                if (entry.getValue() > maxCount) {
                    mostCommonLetter = entry.getKey();
                    maxCount = entry.getValue();
                }
            }

            System.out.println("Den vanligaste bokstaven i " + fileName + " är: " + mostCommonLetter + " " + maxCount + " gånger.");

            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " kunde inte hittas!");
        }
    }
}
