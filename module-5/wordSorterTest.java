//Brennan Cheatwood
//CSD420 - Assignment 5.2 - Test Code
//4/18/26

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class wordSorterTest {


    //helper method to create a temp test file with given content
    private static void createTestFile(String filename, String content) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write(content);
        writer.close();
    }

    //TEST 1: make sure the duplicates are removed

    public static boolean testDuplicatesRemoved() throws Exception {
        String testFile = "test_duplicates.txt";
        createTestFile(testFile, "apple banana apple orange banana apple");

        TreeSet<String> result = wordSorter.getUniqueWords(testFile);

        //Should only have 3 unique words
        boolean passed = result.size() == 3;
        System.out.println("TEST 1 - Duplicates removed: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("   Expected 3 unique words, got: " + result.size());
        }
        return passed;
    }

    //TEST 2 - Make sure words are in ascending order
    public static boolean testAscendingOrder() throws Exception {
        String testFile = "test_ascending.txt";
        createTestFile(testFile, "zebra apple mango banana");

        TreeSet<String> result = wordSorter.getUniqueWords(testFile);
        List<String> resultList = Arrays.asList(result.toArray(new String[0]));
        List<String> expected = Arrays.asList("apple", "banana", "mango", "zebra");

        boolean passed = resultList.equals(expected);
        System.out.println("TEST 2 - Ascending Order: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("   Expected: " + expected);
            System.out.println("   Got: " + resultList);
        }
        return passed;
    }

    //TEST 3 - Make sure descending order is correct
    public static boolean testDescendingOrder() throws Exception {
        String testFile = "test_descending.txt";
        createTestFile(testFile, "zebra apple mango banana");

        TreeSet<String> result = wordSorter.getUniqueWords(testFile);
        List<String> resultList = Arrays.asList(result.descendingSet().toArray(new String[0]));
        List<String> expected = Arrays.asList("zebra", "mango", "banana", "apple");

        boolean passed = resultList.equals(expected);
        System.out.println("TEST 3 - Descending Order: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("   Expected" + expected);
            System.out.println("   Got: " + resultList);
        }
        return passed;
    }

    //TEST 4 - Make sure punctuation is handled and words are lowercased
    public static boolean testPunctuationAndCase() throws Exception {
        String testFile = "test_punctuation.txt";
        createTestFile(testFile, "Hello, hello! HELLO world.");

        TreeSet<String> result = wordSorter.getUniqueWords(testFile);

        //these should all collapse into "hello"
        boolean passed = result.size() == 2 && result.contains("hello") && result.contains("world");
        System.out.println("TEST 4 - Punctuation & Case Handling: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected [hello, world], got: " + result);
        }
        return passed;
    }

    //TEST 5: make sure an empty file returns no words
    public static boolean testEmptyFile() throws Exception {
        String testFile = "test_empty.txt";
        createTestFile(testFile, "");

        TreeSet<String> result = wordSorter.getUniqueWords(testFile);

        boolean passed = result.isEmpty();
        System.out.println("TEST 5 - Empty File: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("   Expected empty set, got: " + result);
        }
        return passed;

    }

    public static void main(String[] args) throws Exception {
        System.out.println("\n\n----- Running WordSorter Tests -----\n\n");

        int passed = 0;
        int total = 5;

        if (testDuplicatesRemoved()) passed++;
        if (testAscendingOrder()) passed++;
        if (testDescendingOrder()) passed++;
        if (testPunctuationAndCase()) passed++;
        if (testEmptyFile()) passed++;

        System.out.println("\n\n==== RESULTS: " + passed + "/" + total + " TESTS PASSED ====");
    }
}