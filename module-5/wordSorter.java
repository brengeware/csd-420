//Brennan Cheatwood
//CSD420 - Assignment 5.2
//4/18/26



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class wordSorter {

    public static TreeSet<String> getUniqueWords(String filename) throws FileNotFoundException {
        TreeSet<String> words = new TreeSet<>();
        Scanner scanner = new Scanner(new File(filename));

        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase().replaceAll("[^a-zA-Z]", "");
            if (!word.isEmpty()) {
                words.add(word);
            }
        }

        scanner.close();
        return words;
    }

    public static void main(String[] args) {
        String filename = "collection_of_words.txt";

        try {
            TreeSet<String> words = getUniqueWords(filename);
            //Ascending order (default)
            System.out.println("=== Non-Duplicate Words in Ascending Order ===");
            for (String word : words) {
                System.out.println(word);
            }

            //Descending order using descendingSet()
            System.out.println("\n=== Non-Duplicate Words in Descending Order ===");
            for (String word : words.descendingSet()) {
                System.out.println(word);
            }



        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found -> " + filename);
        }
    }
}

