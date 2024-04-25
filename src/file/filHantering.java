package file;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Filhantering klassen
public class filHantering {
    Scanner scan = new Scanner(System.in);

    // Filnamn för alla textfilen
    String textFileName = "text.txt";
    String personFileName = "personer.txt";
    String wordsFileName = "words.txt";
    String svenskaOrdFileName = "svenska-ord.txt";

    // Filobjekt för alla textfilen
    File textFileObject;
    File personFileObject;
    File wordsFileObject;
    File svenskaOrdFileObject;
    FileWriter myWriter;

    // Konstruktor
    public filHantering() {
        System.out.println("Vilken fil vill du använda? (text, personer, words, ord)");
        String val = scan.nextLine(); // Låter dig att skriva ditt val

        if (val.equalsIgnoreCase("text")) {
            // Skapar filobjekten och anropar skriv- och läsmetoden
            textFileObject = new File(textFileName);
            write2File(textFileName);
            readFromFile(textFileObject);
        } else if (val.equalsIgnoreCase("personer")) {
            // Skapar filobjekten och anropar läsmetoden
            personFileObject = new File(personFileName);
            readFromFile(personFileObject);
        } else if (val.equalsIgnoreCase("words")) {
            // Skapar filobjekten och anropar metoden att hitta längsta ord och vanligaste bokstaven
            wordsFileObject = new File(wordsFileName);
            findLongestWord(wordsFileName);
            findMostCommonLetter(wordsFileName);
        } else if (val.equalsIgnoreCase("ord")) {
            // Skapar filobjekten och anropar metoden att hitta längsta ord och vanligaste bokstaven
            svenskaOrdFileObject = new File(svenskaOrdFileName);
            findLongestWord(svenskaOrdFileName);
            findMostCommonLetter(svenskaOrdFileName);
        } else {
            // Talar om att svaret är ogiltig
            System.out.println("Ogiltig svar");
        }
    }

    // Privat metod för att läsa en fil
    private void readFromFile(File file) {
        try {
            // Skapar en scanner för att läsa filen
            Scanner fileScanner = new Scanner(file);
            // Skriver ut namnet på filen
            System.out.println("Innehållet i " + file.getName() + ": ");
            // Loopar genom varje rad i filen och skriver ut innehållet
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }

            // Stänger scanner
            fileScanner.close();

        } catch (FileNotFoundException e) {
            // Hantera om filen inte hittas
            System.out.println(file.getName() + "kunde inte hittas!");
        }

    }

    // Privat metod för att skriva i en fil
    private void write2File(String fileName) {
        try {
            // Skapar en FileWriter för att skriva i filen
            myWriter = new FileWriter(fileName, true);

            // Frågar användaren vad den vill skriva
            System.out.println("Vad vill du skriva i filen?");
            String input = scan.nextLine();
            // Skriver användarens inmatning
            myWriter.write(input + "\n");

            // Stänger FileWriter
            myWriter.close();

        } catch (IOException e) {
            // Hantera om filen inte hittas
            System.out.println("Kunde inte skriva till " + fileName + "!");
            e.printStackTrace();
        }
    }

    // Privat metod för att hitta längsta ordet i en fil
    private void findLongestWord (String fileName) {
        try {
            // Skapar ett filobjekt för den valda filen
            File fileObject = new File(fileName);
            // Skapar en scanner för att läsa filen
            Scanner wordScanner = new Scanner(fileObject);

            // Variabel för att spara det längsta ordet
            String longestWord = "";
            // loopar genom varje ord i filen
            while (wordScanner.hasNext()) {
                String word = wordScanner.next();
                // Jämför längden på det nuvarande ordet med den längsta ordet
                // Om nuvarande ordet är längre blir det längsta ordet
                if (word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }

            // Om längsta ordet inte är tomt skrivs det ut
            if (!longestWord.isEmpty()) {
                System.out.println("Det längsta ordet i " + fileName + " är: " + longestWord);
            } else {
                System.out.println(fileName + " innehåller inga ord");
            }
            // Stänger scanner
            wordScanner.close();

        } catch (FileNotFoundException e) {
            // Hantera om filen inte hittas
            System.out.println(fileName + " kunde inte hittas!");
        }
    }

    // Privat metod för att hitta den vanligaste bokstaven i en fil
    private void findMostCommonLetter (String fileName) {
        try {
            // Skapar ett filobjekt för den valda filen
            File fileObject = new File(fileName);
            // Skapar en scanner för att läsa filen
            Scanner scan = new Scanner(fileObject);

            // Skapar en HashMap för att räkna förekomsten av varje bokstav
            Map<Character, Integer> lettercount = new HashMap<>();

            // Loopar genom varje rad i filen
            while (scan.hasNextLine()) {
                // Läser nästa rad från filen
                String line = scan.nextLine();
                // Loopar genom varje tecken i raden
                for (char c : line.toCharArray()) {
                    // Kontrollerar om tecknet är en bokstav
                    if (Character.isLetter(c)) {
                        // Omvandla tecknet till små bokstäver för att undvika skillnader i storlek
                        char lowercaseC = Character.toLowerCase(c);
                        // Ökar räknaren för den aktuella bokstaven i HashMap
                        lettercount.put(lowercaseC, lettercount.getOrDefault(lowercaseC, 0) + 1);
                    }
                }
            }

            // Variabler som sparar den vanligaste bokstaven och dess antalet de förekommer
            char mostCommonLetter = ' ';
            int maxCount = 0;

            // Loopar genom HashMap som hittar den bokstav som förekommer flest gånger
            for (Map.Entry<Character, Integer> entry : lettercount.entrySet()) {
                if (entry.getValue() > maxCount) {
                    mostCommonLetter = entry.getKey();
                    maxCount = entry.getValue();
                }
            }

            // Skriver ut den vanligaste bokstaven och förekomstantal
            System.out.println("Den vanligaste bokstaven i " + fileName + " är: " + mostCommonLetter + " " + maxCount + " gånger.");

            // Stänger scanner
            scan.close();

        } catch (FileNotFoundException e) {
            // Hantera om filen inte hittas
            System.out.println(fileName + " kunde inte hittas!");
        }
    }
}
